package org.identity.identityserver.exception;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author BUI_QUANG_HIEU
 * 13/5/2025
 * CommonError.java
 */
public class CommonError {

    public static final BusinessErrorCode BAD_REQUEST =
            new BusinessErrorCode(4000, "Bad request.", 400);
    public static final BusinessErrorCode INTERNAL_SERVER_ERROR =
            new BusinessErrorCode(5000, "Internal Server Error.", 500);

    public static final BusinessErrorCode FORBIDDEN =
            new BusinessErrorCode(4030, "Access denied. You are unauthorized to access this resource.", 403);

    public static final BusinessErrorCode UNAUTHORIZED =
            new BusinessErrorCode(4010, "Access is denied. You need to login before using the resource.", 401);

    public static final BusinessErrorCode DATA_NOT_FOUND = new BusinessErrorCode(4040, "Data not found.", 400);

    static {
        var codes = new HashSet<Integer>();
        var duplications = Arrays.stream(CommonError.class.getDeclaredFields())
                .filter(f -> Modifier.isStatic(f.getModifiers()) && f.getType().equals(BusinessErrorCode.class))
                .map(f -> {
                    try {
                        return ((BusinessErrorCode) f.get(null)).getCode();
                    } catch (IllegalAccessException e) {
//                        log.error("Can't load error code into map", e);
                        throw new RuntimeException(e);
                    }
                })
                .filter(c -> !codes.add(c))
                .toList();
        if (!duplications.isEmpty()) {
            throw new RuntimeException("Found error code duplication: " + duplications);
        }
    }
}
