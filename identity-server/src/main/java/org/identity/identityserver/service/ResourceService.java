package org.identity.identityserver.service;

import org.identity.identityserver.model.filter.ResourceFilter;
import org.identity.identityserver.model.response.ResourceResponse;
import org.identity.identityserver.model.response.base.Response;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/27/2025
 * ResourceService.java
 */
public interface ResourceService {

    Response<List<ResourceResponse>> getAllResources(ResourceFilter filter, Pageable pageable);

    Response<ResourceResponse> getResourceById(UUID id);
}
