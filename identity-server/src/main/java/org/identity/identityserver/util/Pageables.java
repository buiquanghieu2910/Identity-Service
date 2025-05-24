package org.identity.identityserver.util;

import org.identity.identityserver.exception.BusinessException;
import org.identity.identityserver.exception.ErrorCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * @author BUI_QUANG_HIEU
 * 5/24/2025
 * Pageables.java
 */
public class Pageables {
    private static final Pattern ORDER_PATTERN = Pattern.compile("\\G(\\w+):(ASC|DESC)(,+|$)");

    public static Pageable of(int page, int size, String sorts) {
        if (page < 0) {
            throw new BusinessException(ErrorCode.INVALID_PAGE, "Page index: " + page + " less than zero");
        }
        if (size < 1) {
            throw new BusinessException(ErrorCode.INVALID_PAGE, "Size index: " + page + " less than one");
        }
        var sort = ObjectUtils.isEmpty(sorts) ? Sort.unsorted() : parseSort(sorts);
        return PageRequest.of(--page, size, sort);
    }

    private static Sort parseSort(String sorts) {
        var orders = new ArrayList<Sort.Order>();
        var matcher = ORDER_PATTERN.matcher(sorts);
        String fieldName;
        Sort.Direction direction;
        while (matcher.find()) {
            fieldName = matcher.group(1);
            direction = Sort.Direction.valueOf(matcher.group(2));
            orders.add(new Sort.Order(direction, fieldName));
        }
        return orders.isEmpty() ? Sort.unsorted() : Sort.by(orders);
    }

    private Pageables() {
        throw new UnsupportedOperationException();
    }
}
