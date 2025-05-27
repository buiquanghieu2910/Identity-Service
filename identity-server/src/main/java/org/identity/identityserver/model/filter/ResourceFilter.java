package org.identity.identityserver.model.filter;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/27/2025
 * ResourceFilter.java
 */
@Getter
@Setter
@Accessors(chain = true)
public class ResourceFilter {
    @NotNull
    private UUID applicationId;
}
