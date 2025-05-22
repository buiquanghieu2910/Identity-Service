package org.identity.identityserver.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.identity.identityserver.model.entity.base.BaseEntity;
import org.identity.identityserver.model.enumable.IdentityType;

import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * User.java
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user_entity")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(length = 50, unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String identifier;
    @Enumerated(EnumType.STRING)
    private IdentityType identityType;
}
