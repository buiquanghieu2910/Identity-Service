package org.identity.identityserver.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * ResourceUri.java
 */
@Getter
@Setter
@Entity
@Table(name = "resource_uri")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
public class ResourceUri {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID resourceId;
    private String uri;
}
