package org.identity.identityserver.component;

import lombok.RequiredArgsConstructor;
import org.identity.identityserver.model.entity.OAuthClientRegistration;
import org.identity.identityserver.repository.OAuthClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * @author BUI_QUANG_HIEU
 * 5/23/2025
 * DatabaseClientRegistrationRepository.java
 */
@Component("dynamicClientRegistrationRepository")
@RequiredArgsConstructor
public class DatabaseClientRegistrationRepository implements ClientRegistrationRepository, Iterable<ClientRegistration> {
    private final OAuthClientRegistrationRepository oAuthClientRegistrationRepository;

    @Override
    public ClientRegistration findByRegistrationId(String registrationId) {
        OAuthClientRegistration entity = oAuthClientRegistrationRepository
                .findByRegistrationId(registrationId)
                .orElseThrow(() -> new IllegalArgumentException("Unknown registrationId: " + registrationId));

        return mapToClientRegistration(entity);
    }

    @Override
    public Iterator<ClientRegistration> iterator() {
        return oAuthClientRegistrationRepository.findAll().stream().map(this::mapToClientRegistration).iterator();
    }

    private ClientRegistration mapToClientRegistration(OAuthClientRegistration entity) {
        return ClientRegistration.withRegistrationId(entity.getRegistrationId())
                .clientId(entity.getClientId())
                .clientSecret(entity.getClientSecret())
                .authorizationUri(entity.getAuthorizationUri())
                .tokenUri(entity.getTokenUri())
                .jwkSetUri(entity.getJwkSetUri())
                .userInfoUri(entity.getUserInfoUri())
                .userNameAttributeName(entity.getUserNameAttributeName())
                .redirectUri(entity.getRedirectUriTemplate())
                .scope(entity.getScope().split(","))
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .build();
    }
}

