package org.identity.identityserver.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.identity.identityserver.model.response.base.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

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

    @ExceptionHandler(Exception.class)
    protected void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.error(e.getMessage());
        handle(e, CommonError.INTERNAL_SERVER_ERROR, request, response);
    }

    @ExceptionHandler(BusinessException.class)
    protected void handleBusinessException(BusinessException e, HttpServletRequest request,
                                           HttpServletResponse response) throws IOException {
        log.error(e.getMessage());
        handle(e, e.getErrorCode(), request, response);
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
