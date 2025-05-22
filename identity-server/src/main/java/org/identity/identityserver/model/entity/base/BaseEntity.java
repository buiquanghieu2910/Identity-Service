package org.identity.identityserver.model.entity.base;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * BaseEntity.java
 */
@Data
@Accessors(chain = true)
@MappedSuperclass
public abstract class BaseEntity {
    @CreationTimestamp
    private OffsetDateTime createdAt;
    private String createdBy;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
    private String updatedBy;
}
