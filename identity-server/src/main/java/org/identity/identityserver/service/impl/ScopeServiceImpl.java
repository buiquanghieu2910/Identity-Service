package org.identity.identityserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.identity.identityserver.exception.BusinessException;
import org.identity.identityserver.exception.ErrorCode;
import org.identity.identityserver.model.mapper.IdNameDTO2IdNameResponseMapper;
import org.identity.identityserver.model.mapper.Scope2ScopeResponseMapper;
import org.identity.identityserver.model.response.IdNameResponse;
import org.identity.identityserver.model.response.ScopeResponse;
import org.identity.identityserver.model.response.base.Response;
import org.identity.identityserver.repository.PermissionRepository;
import org.identity.identityserver.repository.ResourceRepository;
import org.identity.identityserver.repository.ScopeRepository;
import org.identity.identityserver.service.ScopeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/27/2025
 * ScopeServiceImpl.java
 */
@Service
@RequiredArgsConstructor
public class ScopeServiceImpl implements ScopeService {
    private final ScopeRepository scopeRepository;
    private final ResourceRepository resourceRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public Response<List<ScopeResponse>> getScopesByApplicationId(UUID applicationId) {
        return Response.ofSucceeded(
                scopeRepository.findByApplicationId(applicationId)
                        .stream()
                        .map(Scope2ScopeResponseMapper.INSTANCE::map)
                        .toList()
        );
    }

    @Override
    public Response<List<IdNameResponse>> getIdNamesByApplicationId(UUID applicationId) {
        return Response.ofSucceeded(
                scopeRepository.getIdNameDTOsByApplicationId(applicationId)
                        .stream()
                        .map(IdNameDTO2IdNameResponseMapper.INSTANCE::map)
                        .toList()
        );
    }

    @Override
    public Response<List<IdNameResponse>> getIdNamesByResourceId(UUID resourceId) {
        return Response.ofSucceeded(
                scopeRepository.getIdNameDTOsByResourceId(resourceId)
                        .stream()
                        .map(IdNameDTO2IdNameResponseMapper.INSTANCE::map)
                        .toList()
        );
    }

    @Override
    public Response<ScopeResponse> getScopeById(UUID id) {
        var scope = scopeRepository.findById(id).orElseThrow(
                () -> new BusinessException(ErrorCode.DATA_NOT_FOUND, "Scope not found with id: " + id)
        );
        var resources = resourceRepository.getIdNameDTOsByScopeId(id)
                .stream()
                .map(IdNameDTO2IdNameResponseMapper.INSTANCE::map)
                .toList();
        var permissions = permissionRepository.getIdNameDTOsByScopeId(id)
                .stream()
                .map(IdNameDTO2IdNameResponseMapper.INSTANCE::map)
                .toList();
        var response = Scope2ScopeResponseMapper.INSTANCE.map(scope)
                .setResources(resources)
                .setPermissions(permissions);
        return Response.ofSucceeded(response);
    }
}
