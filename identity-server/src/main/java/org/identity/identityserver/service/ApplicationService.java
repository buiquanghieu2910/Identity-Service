package org.identity.identityserver.service;

import org.identity.identityserver.model.response.ApplicationResponse;
import org.identity.identityserver.model.response.base.Response;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/24/2025
 * ApplicationService.java
 */
public interface ApplicationService {
    String getApplicationNameByClientId(String clientId);

    Response<List<ApplicationResponse>> getAllApplication(Pageable pageable);

    Response<ApplicationResponse> getApplicationById(UUID id);
}
