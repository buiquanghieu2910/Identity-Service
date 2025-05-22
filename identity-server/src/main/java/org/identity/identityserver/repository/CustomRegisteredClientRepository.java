package org.identity.identityserver.repository;

import lombok.extern.slf4j.Slf4j;
import org.identity.identityserver.model.entity.Application;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationException;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.Optional;
import java.util.Set;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * CustomRegisteredClientRepository.java
 */
@Slf4j
public class CustomRegisteredClientRepository implements RegisteredClientRepository {
    private final PasswordEncoder passwordEncoder;
    private final ApplicationRepository applicationRepository;

    public CustomRegisteredClientRepository(PasswordEncoder passwordEncoder, ApplicationRepository applicationRepository) {
        this.passwordEncoder = passwordEncoder;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RegisteredClient findById(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        var applicationOpt = applicationRepository.findByClientId(clientId);
        return mapToRegisteredClient(applicationOpt);
    }

    private RegisteredClient mapToRegisteredClient(Optional<Application> applicationOpt) {
        if (applicationOpt.isEmpty()) {
            throw new OAuth2AuthorizationCodeRequestAuthenticationException(
                    new OAuth2Error("invalid_client", "Client not found", null),
                    null
            );
        }
        var application = applicationOpt.get();
        return RegisteredClient.withId(application.getId().toString())
                .clientId(application.getClientId())
                .clientSecret(passwordEncoder.encode(application.getClientSecret()))
                .clientAuthenticationMethods(methods ->
                        methods.addAll(Set.of(
                                ClientAuthenticationMethod.NONE,
                                ClientAuthenticationMethod.CLIENT_SECRET_BASIC,
                                ClientAuthenticationMethod.CLIENT_SECRET_POST
                        ))
                )
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.TOKEN_EXCHANGE)
                .redirectUris(uris -> uris.addAll(application.getRedirectUris()))
                .scopes(scopes -> scopes.addAll(Set.of(application.getScope().split(" "))))
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(false)
                        .build())
                .tokenSettings(TokenSettings.builder()
                        .reuseRefreshTokens(true)
                        .accessTokenTimeToLive(Duration.ofHours(1)) // TTL of access_token
                        .refreshTokenTimeToLive(Duration.ofHours(12)) // TTL of refresh_token
                        .build())
                .build();
    }
}
