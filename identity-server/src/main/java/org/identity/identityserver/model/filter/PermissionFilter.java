package org.identity.identityserver.model.filter;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/29/2025
 * PermissionFilter.java
 */
@Getter
@Setter
@Accessors(chain = true)
public class PermissionFilter {
    @NotNull
    private UUID applicationId;
}
