package org.identity.identityserver.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * CustomTokenSuccessHandler.java
 */
public class CustomTokenSuccessHandler implements AuthenticationSuccessHandler {
    private final Duration refreshTokenTTL;

    public CustomTokenSuccessHandler(Duration refreshTokenTTL) {
        this.refreshTokenTTL = refreshTokenTTL;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (!(authentication instanceof OAuth2AccessTokenAuthenticationToken tokenAuthentication)) {
            return;
        }

        OAuth2AccessTokenResponse tokenResponse = (OAuth2AccessTokenResponse) tokenAuthentication.getCredentials();

        Map<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("access_token", tokenResponse.getAccessToken().getTokenValue());
        responseMap.put("token_type", tokenResponse.getAccessToken().getTokenType().getValue());
        responseMap.put("expires_in", tokenResponse.getAccessToken().getExpiresAt().getEpochSecond() -
                tokenResponse.getAccessToken().getIssuedAt().getEpochSecond());
        if (tokenResponse.getRefreshToken() != null) {
            responseMap.put("refresh_token", tokenResponse.getRefreshToken().getTokenValue());
            responseMap.put("refresh_token_expires_in", refreshTokenTTL.getSeconds());
        }
        responseMap.put("scope", String.join(" ", tokenResponse.getAccessToken().getScopes()));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        new ObjectMapper().writeValue(response.getWriter(), responseMap);
    }
}