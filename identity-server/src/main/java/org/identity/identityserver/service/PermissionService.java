package org.identity.identityserver.service;

import org.identity.identityserver.model.filter.PermissionFilter;
import org.identity.identityserver.model.response.PermissionResponse;
import org.identity.identityserver.model.response.base.Response;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/29/2025
 * PermissionService.java
 */
public interface PermissionService {
    Response<List<PermissionResponse>> getAllPermissions(PermissionFilter filter, Pageable pageable);

    Response<PermissionResponse> getAllPermissionById(UUID id);
}
