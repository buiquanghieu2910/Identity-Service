package org.identity.identityserver.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.identity.identityserver.model.entity.embedded.UserApplicationId;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * UserEntityApplication.java
 */
@Data
@Entity
@Table(name = "user_application")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
public class UserEntityApplication {
    @EmbeddedId
    private UserApplicationId id;
}
