package org.identity.identityserver.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.identity.identityserver.converter.SetToStringConverter;
import org.identity.identityserver.model.entity.base.BaseEntity;
import org.identity.identityserver.model.enumable.Status;

import java.util.Set;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * Application.java
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "application")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
public class Application extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String description;
    @Column(length = 50, unique = true)
    private String clientId;
    private String clientSecret;
    @Convert(converter = SetToStringConverter.class)
    private Set<String> redirectUris;
    private String scope;
    private Boolean googleLoginEnable;
    @Convert(converter = SetToStringConverter.class)
    private Set<String> webOrigins;
    @Enumerated(EnumType.STRING)
    private Status status;
}
