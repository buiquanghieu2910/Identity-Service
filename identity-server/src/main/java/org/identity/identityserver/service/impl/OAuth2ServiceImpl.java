package org.identity.identityserver.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.identity.identityserver.model.request.OAuth2LoginUserPassRequest;
import org.identity.identityserver.model.response.OAuth2TokenResponse;
import org.identity.identityserver.repository.CustomRegisteredClientRepository;
import org.identity.identityserver.repository.ApplicationRepository;
import org.identity.identityserver.service.OAuth2Service;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/14/2025
 * OAuth2ServiceImpl.java
 */
@Service
@RequiredArgsConstructor
public class OAuth2ServiceImpl implements OAuth2Service {
    private final AuthenticationManager authenticationManager;
    private final OAuth2TokenGenerator<?> oAuth2TokenGenerator;
    private final OAuth2AuthorizationService oAuth2AuthorizationService;
    private final ApplicationRepository oAuthClientRepository;
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;
    private CustomRegisteredClientRepository customRegisteredClientRepository;

    @PostConstruct
    public void init() {
        this.customRegisteredClientRepository = new CustomRegisteredClientRepository(passwordEncoder, oAuthClientRepository);
    }


    @Override
    public ResponseEntity<?> authenticationUserPass(OAuth2LoginUserPassRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Load RegisteredClient
        var registeredClient = customRegisteredClientRepository.findByClientId(request.getClientId());
        assert registeredClient != null;

        Instant now = Instant.now();
        Instant expiresAt = now.plus(registeredClient.getTokenSettings().getAccessTokenTimeToLive());

        // Create JWT claims set
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .id(UUID.randomUUID().toString())
                .issuer("http://localhost:8989")
                .subject(authentication.getName())
                .issuedAt(now)
                .expiresAt(expiresAt)
                .subject(authentication.getName())
                .claim("scope", registeredClient.getScopes())
                .build();
        Jwt jwt = jwtEncoder.encode(JwtEncoderParameters.from(claimsSet));

        // Create access token
        OAuth2AccessToken accessToken = new OAuth2AccessToken(
                OAuth2AccessToken.TokenType.BEARER,
                jwt.getTokenValue(),
                jwt.getIssuedAt(),
                jwt.getExpiresAt(),
                registeredClient.getScopes()
        );

        // Generate refresh token if the client supports it
        OAuth2RefreshToken refreshToken = null;
        if (registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.REFRESH_TOKEN)) {
            OAuth2TokenContext refreshTokenContext = DefaultOAuth2TokenContext.builder()
                    .registeredClient(registeredClient)
                    .principal(authentication)
                    .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                    .authorizationGrant(authentication)
                    .build();
            refreshToken = (OAuth2RefreshToken) oAuth2TokenGenerator.generate(refreshTokenContext);
        }

        // Save the access token and refresh token to the authorization service
        OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient)
                .principalName(authentication.getName())
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .accessToken(accessToken);
        if (refreshToken != null) {
            authorizationBuilder.refreshToken(refreshToken);
        }
        oAuth2AuthorizationService.save(authorizationBuilder.build());

        var response = new OAuth2TokenResponse()
                .setIdToken(jwt.getId())
                .setAccessToken(accessToken.getTokenValue())
                .setTokenType(accessToken.getTokenType().getValue())
                .setExpiresIn(Objects.requireNonNull(accessToken.getExpiresAt()).getEpochSecond() - Objects.requireNonNull(accessToken.getIssuedAt()).getEpochSecond())
                .setScope(String.join(" ", registeredClient.getScopes()));
        if (refreshToken != null) {
            response.setRefreshToken(refreshToken.getTokenValue())
                    .setRefreshExpiresIn(Objects.requireNonNull(refreshToken.getExpiresAt()).getEpochSecond() - Objects.requireNonNull(refreshToken.getIssuedAt()).getEpochSecond());
        }
        return ResponseEntity.ok(response);
    }
}
