package org.identity.identityserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.identity.identityserver.exception.BusinessException;
import org.identity.identityserver.exception.ErrorCode;
import org.identity.identityserver.model.mapper.Application2ApplicationResponseMapper;
import org.identity.identityserver.model.response.ApplicationResponse;
import org.identity.identityserver.model.response.base.Response;
import org.identity.identityserver.repository.ApplicationRepository;
import org.identity.identityserver.service.ApplicationService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/24/2025
 * ApplicationServiceImpl.java
 */
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;

    @Override
    public String getApplicationNameByClientId(String clientId) {
        return applicationRepository.findNameByClientId(clientId);
    }

    @Override
    public Response<List<ApplicationResponse>> getAllApplication(Pageable pageable) {
        var applications = applicationRepository.findAll(pageable);
        return Response.ofSucceeded(applications.map(Application2ApplicationResponseMapper.INSTANCE::map));
    }

    @Override
    public Response<ApplicationResponse> getApplicationById(UUID id) {
        var application = applicationRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.DATA_NOT_FOUND, "Application not found with id: " + id));
        return Response.ofSucceeded(Application2ApplicationResponseMapper.INSTANCE.map(application));
    }
}
