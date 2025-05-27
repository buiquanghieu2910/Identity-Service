package org.identity.identityserver.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.identity.identityserver.model.response.base.CommonResponse;

import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/27/2025
 * ScopeResponse.java
 */
@Getter
@Setter
@Accessors(chain = true)
public class ScopeResponse extends CommonResponse {
    private UUID applicationId;
    private String name;
    private String description;
}

