package org.identity.identityserver.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author BUI_QUANG_HIEU
 * 5/14/2025
 * OAuth2LoginUserPassRequest.java
 */
@Data
@Accessors(chain = true)
public class OAuth2LoginUserPassRequest {
    private String username;
    private String password;
    private String clientId;
    private String clientSecret;
}
