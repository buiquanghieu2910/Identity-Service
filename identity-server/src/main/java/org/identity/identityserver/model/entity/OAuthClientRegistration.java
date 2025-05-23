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
 * 5/23/2025
 * OAuthClientRegistration.java
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "oauth_client_registration")
@Accessors(chain = true)
@DynamicInsert
@DynamicUpdate
public class OAuthClientRegistration extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String registrationId; // "google", "github"
    private String clientId;
    private String clientSecret;

    private String authorizationUri;
    private String tokenUri;
    private String userInfoUri;
    private String jwkSetUri;

    private String redirectUriTemplate;
    private String scope; // "email,profile"
    private String userNameAttributeName;
}
