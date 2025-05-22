package org.identity.identityserver.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.identity.identityserver.model.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author BUI_QUANG_HIEU
 * 5/18/2025
 * UserDetailModel.java
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class UserDetailModel extends UserEntity implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
