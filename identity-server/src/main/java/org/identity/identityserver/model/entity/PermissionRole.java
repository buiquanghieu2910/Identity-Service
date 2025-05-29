package org.identity.identityserver.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.identity.identityserver.model.entity.embedded.PermissionRoleId;

/**
 * @author BUI_QUANG_HIEU
 * 5/29/2025
 * PermissionRole.java
 */
@Data
@Entity
@Table(name = "permission_role")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
public class PermissionRole {
    @EmbeddedId
    private PermissionRoleId id;
}
