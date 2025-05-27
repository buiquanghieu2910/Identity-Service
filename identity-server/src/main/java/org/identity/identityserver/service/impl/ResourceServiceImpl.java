package org.identity.identityserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.identity.identityserver.exception.BusinessException;
import org.identity.identityserver.exception.ErrorCode;
import org.identity.identityserver.model.filter.Filters;
import org.identity.identityserver.model.filter.ResourceFilter;
import org.identity.identityserver.model.mapper.Resource2ResourceResponseMapper;
import org.identity.identityserver.model.response.ResourceResponse;
import org.identity.identityserver.model.response.base.Response;
import org.identity.identityserver.repository.ApplicationRepository;
import org.identity.identityserver.repository.ResourceRepository;
import org.identity.identityserver.repository.ResourceUriRepository;
import org.identity.identityserver.service.ResourceService;
import org.identity.identityserver.service.ScopeService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/27/2025
 * ResourceServiceImpl.java
 */
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;
    private final ResourceUriRepository resourceUriRepository;
    private final ApplicationRepository applicationRepository;
    private final ScopeService scopeService;

    @Override
    public Response<List<ResourceResponse>> getAllResources(ResourceFilter filter, Pageable pageable) {
        applicationRepository.findById(filter.getApplicationId())
                .orElseThrow(() -> new BusinessException(ErrorCode.DATA_NOT_FOUND, "Application not found with id: " + filter.getApplicationId()));
        var data = resourceRepository.findAll(Filters.toSpecification(filter), pageable);
        return Response.ofSucceeded(data.map(Resource2ResourceResponseMapper.INSTANCE::map));
    }

    @Override
    public Response<ResourceResponse> getResourceById(UUID id) {
        var resource = resourceRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.DATA_NOT_FOUND, "Resource not found with id: " + id));
        var uris = resourceUriRepository.getUrisByResourceId(id);
        var scopeIds = scopeService.getIdsByResourceId(id);
        var response = Resource2ResourceResponseMapper.INSTANCE.map(resource)
                .setUris(uris)
                .setScopeIds(scopeIds);
        return Response.ofSucceeded(response);
    }
}
