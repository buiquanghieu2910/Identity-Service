package org.identity.identityserver.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.identity.identityserver.model.response.base.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * ExceptionController.java
 */
@ControllerAdvice
@Slf4j
public class ExceptionController {
    @Autowired
    private ObjectMapper objectMapper;

    @ExceptionHandler(BusinessException.class)
    protected void handleBusinessException(BusinessException e, HttpServletRequest request,
                                           HttpServletResponse response) throws IOException {
        log.error(e.getMessage());
        handle(e, e.getErrorCode(), request, response);
    }

    @ExceptionHandler(ValidateException.class)
    protected void handleValidateException(ValidateException e, HttpServletRequest request,
                                           HttpServletResponse response) throws IOException {
        handleInvalidParams(e, e.getFieldViolations(), request, response);
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public void handleOptimisticLockException(HttpServletResponse response) throws IOException {
        var errorResponse = Response.ofFailed(ErrorCode.CONFLICT_VERSION, ErrorCode.CONFLICT_VERSION.getMessage());
        writeResponse(response, ErrorCode.CONFLICT_VERSION.getHttpStatus(), errorResponse);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                             HttpServletRequest request,
                                             HttpServletResponse response) throws IOException {
        Map<String, String> violations = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (msg1, msg2) -> msg1 // nếu trùng field
                ));

        handleInvalidParams(new ValidateException(ErrorCode.INVALID_PARAMETERS.getMessage(), violations), violations, request, response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolation(ConstraintViolationException ex,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws IOException {
        Map<String, String> violations = ex.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(
                        v -> v.getPropertyPath().toString(),
                        ConstraintViolation::getMessage,
                        (msg1, msg2) -> msg1
                ));

        handleInvalidParams(new ValidateException(ErrorCode.INVALID_PARAMETERS.getMessage(), violations), violations, request, response);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public void handleMissingRequestParam(MissingServletRequestParameterException ex,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws IOException {
        Map<String, String> violations = Map.of(ex.getParameterName(), ex.getMessage());
        handleInvalidParams(ex, violations, request, response);
    }

    @ExceptionHandler(Exception.class)
    protected void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.error(e.getMessage());
        handle(e, CommonError.INTERNAL_SERVER_ERROR, request, response);
    }

    private void handleInvalidParams(Exception e, Map<String, String> fieldViolations, HttpServletRequest request,
                                     HttpServletResponse response) throws IOException {
        var errorResponse = Response.ofFailed(ErrorCode.INVALID_PARAMETERS, e.getMessage(), fieldViolations);
        writeResponse(response, ErrorCode.INVALID_PARAMETERS.getHttpStatus(), errorResponse);
    }

    private void handle(Exception e, BusinessErrorCode errorCode, HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        var errorResponse = Response.ofFailed(errorCode, e.getMessage());
        writeResponse(response, errorCode.getHttpStatus(), errorResponse);
    }

    private void writeResponse(HttpServletResponse servletResponse, int httpStatus, Response<?> errorResponse) throws IOException {
        servletResponse.setStatus(httpStatus);
        servletResponse.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        byte[] body = objectMapper.writeValueAsBytes(errorResponse);
        servletResponse.setContentLength(body.length);
        servletResponse.getOutputStream().write(body);
    }
}
