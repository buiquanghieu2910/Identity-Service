package org.identity.identityserver.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.identity.identityserver.model.filter.ResourceFilter;
import org.identity.identityserver.model.response.ResourceResponse;
import org.identity.identityserver.model.response.base.Response;
import org.identity.identityserver.service.ResourceService;
import org.identity.identityserver.util.PageableFilter;
import org.identity.identityserver.util.Pageables;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/27/2025
 * ResourceController.java
 */
@RestController
@RequestMapping("/api/v1/resource")
@RequiredArgsConstructor
public class ResourceController {
    private final ResourceService resourceService;

    @GetMapping
    public Response<List<ResourceResponse>> getAllResources(@Valid @ModelAttribute ResourceFilter filter, PageableFilter pageableFilter) {
        return resourceService.getAllResources(filter, Pageables.of(pageableFilter.getPage(), pageableFilter.getSize(), pageableFilter.getSort()));
    }

    @GetMapping("{id}")
    public Response<ResourceResponse> getResourceById(@PathVariable ("id") UUID id) {
        return resourceService.getResourceById(id);
    }
}
