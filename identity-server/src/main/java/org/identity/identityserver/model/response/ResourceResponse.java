package org.identity.identityserver.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.identity.identityserver.model.response.base.CommonResponse;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/27/2025
 * ResourceResponse.java
 */
@Getter
@Setter
@Accessors(chain = true)
public class ResourceResponse extends CommonResponse {
    private UUID applicationId;
    private String name;
    private String description;
    private Set<String> uris;
    private List<UUID> scopeIds;
}

