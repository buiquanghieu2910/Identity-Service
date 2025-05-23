package org.identity.identityserver.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.identity.identityserver.component.AllowedOriginCache;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author BUI_QUANG_HIEU
 * 5/17/2025
 * DynamicCorsFilter.java
 */
@Slf4j
@Component
public class DynamicCorsFilter implements Filter {

    private final AllowedOriginCache allowedOriginCache;

    public DynamicCorsFilter(AllowedOriginCache allowedOriginCache) {
        this.allowedOriginCache = allowedOriginCache;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String origin = req.getHeader("Origin");

        if (origin != null && allowedOriginCache.isAllowed(origin)) {
            res.setHeader("Access-Control-Allow-Origin", origin);
            res.setHeader("Access-Control-Allow-Credentials", "true");
            res.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            res.setHeader("Access-Control-Allow-Headers", "Origin, Authorization, Content-Type");
            if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
                res.setStatus(HttpServletResponse.SC_OK);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
