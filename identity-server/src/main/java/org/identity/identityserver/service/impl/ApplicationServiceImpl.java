package org.identity.identityserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.identity.identityserver.repository.ApplicationRepository;
import org.identity.identityserver.service.ApplicationService;
import org.springframework.stereotype.Service;

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
}
