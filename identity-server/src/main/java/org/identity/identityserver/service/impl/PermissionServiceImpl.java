package org.identity.identityserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.identity.identityserver.exception.BusinessException;
import org.identity.identityserver.exception.ErrorCode;
import org.identity.identityserver.model.filter.Filters;
import org.identity.identityserver.model.filter.PermissionFilter;
import org.identity.identityserver.model.mapper.Permission2PermissionResponseMapper;
import org.identity.identityserver.model.response.PermissionResponse;
import org.identity.identityserver.model.response.base.Response;
import org.identity.identityserver.repository.ApplicationRepository;
import org.identity.identityserver.repository.PermissionRepository;
import org.identity.identityserver.repository.ScopeRepository;
import org.identity.identityserver.service.PermissionService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/29/2025
 * PermissionServiceImpl.java
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final ApplicationRepository applicationRepository;
    private final ScopeRepository scopeRepository;

    @Override
    public Response<List<PermissionResponse>> getAllPermissions(PermissionFilter filter, Pageable pageable) {
        applicationRepository.findById(filter.getApplicationId())
                .orElseThrow(() -> new BusinessException(ErrorCode.DATA_NOT_FOUND, "Application not found with id: " + filter.getApplicationId()));
        var data = permissionRepository.findAll(Filters.toSpecification(filter), pageable)
                .map(Permission2PermissionResponseMapper.INSTANCE::map);
        return Response.ofSucceeded(data);
    }

    @Override
    public Response<PermissionResponse> getAllPermissionById(UUID id) {
        var permission = permissionRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.DATA_NOT_FOUND, "Permission not found with id: " + id));
        var scopeIds = scopeRepository.getIdsByPermissionId(id);
        var response = Permission2PermissionResponseMapper.INSTANCE.map(permission)
                .setScopeIds(scopeIds);
        return Response.ofSucceeded(response);
    }
}
