package org.identity.identityserver.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.identity.identityserver.model.entity.base.BaseEntity;

import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * Scope.java
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "scope")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
public class Scope extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID applicationId;
    private String name;
    private String description;
}
