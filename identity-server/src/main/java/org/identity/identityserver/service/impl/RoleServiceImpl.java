package org.identity.identityserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.identity.identityserver.model.mapper.IdNameDTO2IdNameResponseMapper;
import org.identity.identityserver.model.response.IdNameResponse;
import org.identity.identityserver.model.response.base.Response;
import org.identity.identityserver.repository.RoleRepository;
import org.identity.identityserver.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/30/2025
 * RoleServiceImpl.java
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Response<List<IdNameResponse>> getIdNamesByApplicationId(UUID applicationId) {
        var response = roleRepository.getIdNameDTOsByApplicationId(applicationId)
                .stream()
                .map(IdNameDTO2IdNameResponseMapper.INSTANCE::map)
                .toList();
        return Response.ofSucceeded(response);
    }
}
