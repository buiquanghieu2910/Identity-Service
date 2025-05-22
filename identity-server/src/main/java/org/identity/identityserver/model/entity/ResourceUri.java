package org.identity.identityserver.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * ResourceUri.java
 */
@Data
@Entity
@Table(name = "resource_uri")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
public class ResourceUri {
    @Id
    private String uri;
    private UUID resourceId;
}
