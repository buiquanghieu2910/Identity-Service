package org.identity.identityserver.service;

import org.identity.identityserver.model.request.OAuth2LoginUserPassRequest;
import org.springframework.http.ResponseEntity;

/**
 * @author BUI_QUANG_HIEU
 * 5/14/2025
 * OAuth2Service.java
 */
public interface OAuth2Service {
    ResponseEntity<?> authenticationUserPass(OAuth2LoginUserPassRequest request);
}
