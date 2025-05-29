package org.identity.identityserver.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.identity.identityserver.model.filter.PermissionFilter;
import org.identity.identityserver.model.response.PermissionResponse;
import org.identity.identityserver.model.response.base.Response;
import org.identity.identityserver.service.PermissionService;
import org.identity.identityserver.util.PageableFilter;
import org.identity.identityserver.util.Pageables;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/29/2025
 * PermissionController.java
 */
@RestController
@RequestMapping("/api/v1/permission")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @GetMapping
    public Response<List<PermissionResponse>> getAllPermissions(@Valid @ModelAttribute PermissionFilter filter, PageableFilter pageableFilter) {
        return permissionService.getAllPermissions(filter, Pageables.of(pageableFilter.getPage(), pageableFilter.getSize(), pageableFilter.getSort()));
    }

    @GetMapping("{id}")
    public Response<PermissionResponse> getAllPermissionById(@PathVariable UUID id) {
        return permissionService.getAllPermissionById(id);
    }
}
