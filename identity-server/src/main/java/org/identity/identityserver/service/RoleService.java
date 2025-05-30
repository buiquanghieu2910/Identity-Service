package org.identity.identityserver.service;

import org.identity.identityserver.model.response.IdNameResponse;
import org.identity.identityserver.model.response.base.Response;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/30/2025
 * RoleService.java
 */
public interface RoleService {
    Response<List<IdNameResponse>> getIdNamesByApplicationId(UUID applicationId);
}
