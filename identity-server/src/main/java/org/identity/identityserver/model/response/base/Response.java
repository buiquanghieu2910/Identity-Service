package org.identity.identityserver.model.response.base;

import com.dslplatform.json.CompiledJson;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.identity.identityserver.exception.BusinessErrorCode;
import org.identity.identityserver.exception.BusinessException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author BUI_QUANG_HIEU
 * 6/25/2024
 * Response.java
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private T data;
    private Metadata meta = new Metadata();

    @CompiledJson
    Response(T data, Metadata meta) {
        this.data = data;
        this.meta = meta;
    }

    public Response() {
    }

    public static <T> Response<T> ofSucceeded() {
        return ofSucceeded((T) null);
    }

    @SuppressWarnings("unchecked")
    public static <T> Response<T> ofSucceeded(T data) {
        Response<T> response = new Response<>();
        response.data = data;
        response.meta.code = Metadata.OK_CODE;
        return response;
    }

    @SuppressWarnings("unchecked")
    public static <T> Response<T> ofAccepted(T data) {
        Response<T> response = new Response<>();
        response.data = data;
        response.meta.code = Metadata.ACCEPT_CODE;
        return response;
    }


    public static <T> Response<List<T>> ofSucceeded(Page<T> data) {
        Response<List<T>> response = new Response<>();
        response.data = data.getContent();
        response.meta.code = Metadata.OK_CODE;
        response.meta.page = data.getNumber();
        response.meta.size = data.getSize();
        response.meta.total = data.getTotalElements();
        response.meta.totalPages = data.getTotalPages();
        return response;
    }

    public static Response<Void> ofFailed(BusinessErrorCode errorCode) {
        return ofFailed(errorCode, (String) null);
    }

    public static Response<Void> ofFailed(BusinessErrorCode errorCode, Map<String, String> errors) {
        return ofFailed(errorCode, null, errors);
    }

    public static Response<Void> ofFailed(BusinessErrorCode errorCode, String message) {
        return ofFailed(errorCode, message, null);
    }

    public static Response<Void> ofFailed(BusinessErrorCode errorCode, String message, Map<String, String> error) {
        Response<Void> response = new Response<>();
//        response.meta.code = typeCode.API + errorCode.getCode();
        response.meta.code = String.valueOf(errorCode.getCode());
        response.meta.message = message != null ? message : errorCode.getMessage();
        response.meta.error = error;
        return response;
    }

    public static Response<Void> ofFailed(BusinessException exception) {
        return ofFailed(exception.getErrorCode(), exception.getMessage());
    }

    public T getData() {
        return data;
    }

    public Metadata getMeta() {
        return meta;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Metadata {
        public static final String OK_CODE = "200";
        public static final String ACCEPT_CODE = "202";
        String code;
        Integer page;
        Integer size;
        Long total;
        Integer totalPages;
        String message;
        Map<String, String> error;

        public Metadata() {
        }

        @CompiledJson
        public Metadata(String code, Integer page, Integer size, Long total, Integer totalPages, String message,
                        Map<String, String> error) {
            this.code = code;
            this.page = page;
            this.size = size;
            this.total = total;
            this.totalPages = totalPages;
            this.message = message;
            this.error = error;
        }

        public String getCode() {
            return code;
        }

        public Integer getPage() {
            return page;
        }

        public Integer getSize() {
            return size;
        }

        public Long getTotal() {
            return total;
        }

        public Integer getTotalPages() {
            return totalPages;
        }

        public String getMessage() {
            return message;
        }

        public Map<String, String> getError() {
            return error;
        }
    }

    public interface typeCode {
        String API = "BQH-";
    }
}