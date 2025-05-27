package org.identity.identityserver.service;

import org.identity.identityserver.model.response.IdNameResponse;
import org.identity.identityserver.model.response.ScopeResponse;
import org.identity.identityserver.model.response.base.Response;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/27/2025
 * ScopeService.java
 */
public interface ScopeService {
    Response<List<ScopeResponse>> getScopesByApplicationId(UUID applicationId);

    Response<List<IdNameResponse>> getIdNamesByApplicationId(UUID applicationId);

    List<UUID> getIdsByResourceId(UUID resourceId);
}