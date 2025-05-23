package org.identity.identityserver.filter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author BUI_QUANG_HIEU
 * 5/21/2025
 * RedirectPreservingEntryPoint.java
 */
public class RedirectPreservingEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
        String clientId = request.getParameter("client_id");
        String redirectUri = request.getParameter("redirect_uri");

        String redirectUrl = "/login";
        if (clientId != null) {
            redirectUrl += "?client_id=" + URLEncoder.encode(clientId, StandardCharsets.UTF_8);
        }
        if (redirectUri != null) {
            redirectUrl += "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8);
        }
        response.sendRedirect(redirectUrl);
    }
}
