package org.identity.identityserver.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.identity.identityserver.model.entity.embedded.ResourceScopeId;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * ResourceScope.java
 */
@Data
@Entity
@Table(name = "resource_scope")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
public class ResourceScope {
    @EmbeddedId
    private ResourceScopeId id;
}
