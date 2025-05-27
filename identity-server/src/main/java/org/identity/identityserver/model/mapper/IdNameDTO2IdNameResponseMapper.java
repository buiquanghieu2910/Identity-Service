package org.identity.identityserver.model.mapper;

import org.identity.identityserver.model.dto.IdNameDTO;
import org.identity.identityserver.model.mapper.base.BeanMapper;
import org.identity.identityserver.model.response.IdNameResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author BUI_QUANG_HIEU
 * 5/28/2025
 * IdNameDTO2IdNameResponseMapper.java
 */
@Mapper
public interface IdNameDTO2IdNameResponseMapper extends BeanMapper<IdNameDTO, IdNameResponse> {
    IdNameDTO2IdNameResponseMapper INSTANCE = Mappers.getMapper(IdNameDTO2IdNameResponseMapper.class);
}
