package org.identity.identityserver.controller;

import lombok.RequiredArgsConstructor;
import org.identity.identityserver.model.response.IdNameResponse;
import org.identity.identityserver.model.response.ScopeResponse;
import org.identity.identityserver.model.response.base.Response;
import org.identity.identityserver.service.ScopeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/28/2025
 * ScopeController.java
 */
@RestController
@RequestMapping("/api/v1/scope")
@RequiredArgsConstructor
public class ScopeController {
    private final ScopeService scopeService;

    @GetMapping("by-application-id/{applicationId}")
    public Response<List<ScopeResponse>> getScopesByApplicationId(@PathVariable("applicationId") UUID applicationId) {
        return scopeService.getScopesByApplicationId(applicationId);
    }

    @GetMapping("by-application-id/{applicationId}/id-names")
    public Response<List<IdNameResponse>> getIdNamesByApplicationId(@PathVariable("applicationId") UUID applicationId) {
        return scopeService.getIdNamesByApplicationId(applicationId);
    }
}
