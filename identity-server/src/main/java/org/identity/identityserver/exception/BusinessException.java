package org.identity.identityserver.exception;

import lombok.Getter;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * BusinessException.java
 */
@Getter
public class BusinessException extends RuntimeException {

    private final BusinessErrorCode errorCode;

    public BusinessException(BusinessErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(BusinessErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(BusinessErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
