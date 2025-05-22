package org.identity.identityserver.model.entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * ResourceScopeId.java
 */
@Embeddable
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class ResourceScopeId {
    private UUID resourceId;
    private UUID scopeId;
}
