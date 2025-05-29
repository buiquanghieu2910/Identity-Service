package org.identity.identityserver.model.mapper;

import org.identity.identityserver.model.entity.Permission;
import org.identity.identityserver.model.mapper.base.BeanMapper;
import org.identity.identityserver.model.response.PermissionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author BUI_QUANG_HIEU
 * 5/29/2025
 * Permission2PermissionResponseMapper.java
 */
@Mapper
public interface Permission2PermissionResponseMapper extends BeanMapper<Permission, PermissionResponse> {
    Permission2PermissionResponseMapper INSTANCE = Mappers.getMapper(Permission2PermissionResponseMapper.class);
}
