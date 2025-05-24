package org.identity.identityserver.controller;

import lombok.RequiredArgsConstructor;
import org.identity.identityserver.model.response.ApplicationResponse;
import org.identity.identityserver.model.response.base.Response;
import org.identity.identityserver.service.ApplicationService;
import org.identity.identityserver.util.PageableFilter;
import org.identity.identityserver.util.Pageables;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/24/2025
 * ApplicationController.java
 */
@RestController
@RequestMapping("/api/v1/application")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @GetMapping
    public Response<List<ApplicationResponse>> getAllApplication(PageableFilter pageableFilter) {
        return applicationService.getAllApplication(Pageables.of(pageableFilter.getPage(), pageableFilter.getSize(), pageableFilter.getSort()));
    }

    @GetMapping("{id}")
    public Response<ApplicationResponse> getApplicationById(@PathVariable ("id") UUID id) {
        return applicationService.getApplicationById(id);
    }
}
