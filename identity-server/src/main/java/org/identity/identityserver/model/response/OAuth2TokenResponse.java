package org.identity.identityserver.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author BUI_QUANG_HIEU
 * 5/14/2025
 * OAuth2TokenResponse.java
 */
@Data
@Accessors(chain = true)
public class OAuth2TokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private Long expiresIn;
    @JsonProperty("refresh_expires_in")
    private Long refreshExpiresIn;
    @JsonProperty("id_token")
    private String idToken;
    private String scope;
}
