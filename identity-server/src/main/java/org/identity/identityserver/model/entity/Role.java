package org.identity.identityserver.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.identity.identityserver.model.entity.base.BaseEntity;
import org.identity.identityserver.model.enumable.Status;

import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * Role.java
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "role")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
public class Role extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID applicationId;
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
}
