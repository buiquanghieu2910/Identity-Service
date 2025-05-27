package org.identity.identityserver.model.mapper;

import org.identity.identityserver.model.entity.Scope;
import org.identity.identityserver.model.mapper.base.BeanMapper;
import org.identity.identityserver.model.response.ScopeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author BUI_QUANG_HIEU
 * 5/28/2025
 * Scope2ScopeResponseMapper.java
 */
@Mapper
public interface Scope2ScopeResponseMapper extends BeanMapper<Scope, ScopeResponse> {
    Scope2ScopeResponseMapper INSTANCE = Mappers.getMapper(Scope2ScopeResponseMapper.class);
}
