package org.identity.identityserver.configuration;

import lombok.extern.slf4j.Slf4j;
import org.identity.identityserver.repository.ApplicationRepository;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author BUI_QUANG_HIEU
 * 5/17/2025
 * AllowedOriginCache.java
 */
@Slf4j
@Component
public class AllowedOriginCache {

    private final ApplicationRepository applicationRepository;
    private final Set<String> allowedOrigins = ConcurrentHashMap.newKeySet();

    public AllowedOriginCache(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
        this.refresh();
    }

    public boolean isAllowed(String origin) {
        return allowedOrigins.contains(origin);
    }

    public void refresh() {
        allowedOrigins.clear();
        allowedOrigins.addAll(this.applicationRepository.findAllWebOrigins());
    }

    private String toOrigin(String uriStr) {
        try {
            URI uri = new URI(uriStr);
            return uri.getScheme() + "://" + uri.getHost()
                    + ((uri.getPort() > 0) ? ":" + uri.getPort() : "");
        } catch (Exception e) {
            return null;
        }
    }
}