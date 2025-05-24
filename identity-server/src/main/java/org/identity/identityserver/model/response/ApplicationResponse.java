package org.identity.identityserver.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.identity.identityserver.model.enumable.Status;
import org.identity.identityserver.model.response.base.CommonResponse;

import java.util.Set;

/**
 * @author BUI_QUANG_HIEU
 * 5/24/2025
 * ApplicationResponse.java
 */
@Getter
@Setter
@Accessors(chain = true)
public class ApplicationResponse extends CommonResponse {
    private String name;
    private String description;
    private String clientId;
    private String clientSecret;
    private Set<String> redirectUris;
    private String scope;
    private Boolean googleLoginEnable;
    private Set<String> webOrigins;
    private Status status;
}
