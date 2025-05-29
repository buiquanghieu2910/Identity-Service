package org.identity.identityserver.model.entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/29/2025
 * PermissionScopeId.java
 */
@Embeddable
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class PermissionScopeId {
    private UUID permissionId;
    private UUID scopeId;
}
