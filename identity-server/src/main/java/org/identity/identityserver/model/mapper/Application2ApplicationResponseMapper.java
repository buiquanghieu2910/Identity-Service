package org.identity.identityserver.model.mapper;

import org.identity.identityserver.model.entity.Application;
import org.identity.identityserver.model.mapper.base.BeanMapper;
import org.identity.identityserver.model.response.ApplicationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author BUI_QUANG_HIEU
 * 5/24/2025
 * Application2ApplicationResponseMapper.java
 */
@Mapper
public interface Application2ApplicationResponseMapper extends BeanMapper<Application, ApplicationResponse> {
    Application2ApplicationResponseMapper INSTANCE = Mappers.getMapper(Application2ApplicationResponseMapper.class);
}
