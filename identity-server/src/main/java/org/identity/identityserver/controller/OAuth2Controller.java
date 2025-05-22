package org.identity.identityserver.controller;

import lombok.RequiredArgsConstructor;
import org.identity.identityserver.model.request.OAuth2LoginUserPassRequest;
import org.identity.identityserver.service.OAuth2Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BUI_QUANG_HIEU
 * 5/14/2025
 * OAuth2Controller.java
 */
@RestController
@RequestMapping("/oauth2")
@RequiredArgsConstructor
public class OAuth2Controller {
    private final OAuth2Service oAuth2Service;

    @PostMapping("/token/password")
    public ResponseEntity<?> authenticationUserPass(@RequestBody OAuth2LoginUserPassRequest request) {
        return this.oAuth2Service.authenticationUserPass(request);
    }
}
