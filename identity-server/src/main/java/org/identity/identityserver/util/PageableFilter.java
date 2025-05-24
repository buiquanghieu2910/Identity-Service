package org.identity.identityserver.util;

import lombok.Getter;
import lombok.Setter;

/**
 * @author BUI_QUANG_HIEU
 * 5/24/2025
 * PageableFilter.java
 */

@Getter
@Setter
public class PageableFilter {
    private int page;
    private int size;
    private String sort;
}
