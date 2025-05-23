package org.identity.identityserver.component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author BUI_QUANG_HIEU
 * 5/24/2025
 * CustomOAuth2FailureHandler.java
 */
@Component
public class CustomOAuth2FailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        String clientId = (String) request.getSession().getAttribute("original_client_id");
        String errorMessage = URLEncoder.encode(exception.getMessage(), StandardCharsets.UTF_8);
        String redirectUrl = "/login?error=" + errorMessage;

        if (clientId != null) {
            redirectUrl += "&client_id=" + clientId;
        }

        response.sendRedirect(redirectUrl);
    }
}

