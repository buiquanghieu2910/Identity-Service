package org.identity.identityserver.exception;

import lombok.Value;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * BusinessErrorCode.java
 */
@Value
public class BusinessErrorCode {
    int code;
    String message;
    int httpStatus;
}

