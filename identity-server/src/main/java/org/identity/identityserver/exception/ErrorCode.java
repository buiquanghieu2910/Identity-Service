package org.identity.identityserver.exception;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * ErrorCode.java
 */
public class ErrorCode extends CommonError {
    public static final BusinessErrorCode INVALID_PARAMETERS =
            new BusinessErrorCode(4001, "Invalid Parameters.", 400);

    public static final BusinessErrorCode INVALID_PAGE =
            new BusinessErrorCode(4002, "Invalid page.", 400);

    public static final BusinessErrorCode CONFLICT_VERSION =
            new BusinessErrorCode(4091, "Version mismatch. Please try again.", 409);
}
