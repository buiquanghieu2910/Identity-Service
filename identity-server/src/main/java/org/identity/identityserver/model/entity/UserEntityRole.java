package org.identity.identityserver.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.identity.identityserver.model.entity.embedded.UserRoleId;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * UserEntityRole.java
 */
@Data
@Entity
@Table(name = "user_role")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
public class UserEntityRole {
    @EmbeddedId
    private UserRoleId id;
}
