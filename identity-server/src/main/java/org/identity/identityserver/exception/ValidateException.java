package org.identity.identityserver.exception;

import lombok.Getter;

import java.util.Map;

/**
 * @author BUI_QUANG_HIEU
 * 5/27/2024
 * ValidateException.java
 */
@Getter
public class ValidateException extends RuntimeException {

    private final Map<String, String> fieldViolations;

    public ValidateException(Map<String, String> fieldViolations) {
        this.fieldViolations = fieldViolations;
    }

    public ValidateException(String message, Map<String, String> fieldViolations) {
        super(message);
        this.fieldViolations = fieldViolations;
    }

}
