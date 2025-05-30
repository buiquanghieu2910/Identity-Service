package org.identity.identityserver.controller;

import lombok.RequiredArgsConstructor;
import org.identity.identityserver.model.response.IdNameResponse;
import org.identity.identityserver.model.response.base.Response;
import org.identity.identityserver.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/30/2025
 * RoleController.java
 */
@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("by-application-id/{applicationId}/id-names")
    public Response<List<IdNameResponse>> getIdNamesByApplicationId(@PathVariable UUID applicationId) {
        return roleService.getIdNamesByApplicationId(applicationId);
    }
}
