package org.identity.identityserver.model.mapper;

import org.identity.identityserver.model.entity.Resource;
import org.identity.identityserver.model.mapper.base.BeanMapper;
import org.identity.identityserver.model.response.ResourceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author BUI_QUANG_HIEU
 * 5/27/2025
 * Resource2ResourceResponseMapper.java
 */
@Mapper
public interface Resource2ResourceResponseMapper extends BeanMapper<Resource, ResourceResponse> {
    Resource2ResourceResponseMapper INSTANCE = Mappers.getMapper(Resource2ResourceResponseMapper.class);
}
