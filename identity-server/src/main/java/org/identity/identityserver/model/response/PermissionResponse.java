package org.identity.identityserver.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.identity.identityserver.model.response.base.CommonResponse;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/29/2025
 * PermissionResponse.java
 */
@Getter
@Setter
@Accessors(chain = true)
public class PermissionResponse extends CommonResponse {
    private String name;
    private String description;
    private UUID applicationId;
    private UUID resourceId;
    private List<UUID> scopeIds;
    private List<UUID> roleIds;
}
