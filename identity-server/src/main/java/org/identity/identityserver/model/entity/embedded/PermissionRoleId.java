package org.identity.identityserver.model.entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/29/2025
 * PermissionRoleId.java
 */
@Embeddable
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class PermissionRoleId {
    private UUID permissionId;
    private UUID roleId;
}
