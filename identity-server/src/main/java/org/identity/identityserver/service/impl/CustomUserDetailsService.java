package org.identity.identityserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.identity.identityserver.model.entity.UserEntity;
import org.identity.identityserver.repository.UserEntityRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author BUI_QUANG_HIEU
 * 5/24/2025
 * CustomUserDetailsService.java
 */
public class CustomUserDetailsService implements UserDetailsService {
    private final UserEntityRepository userEntityRepository;

    public CustomUserDetailsService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityRepository.findByUsernameOrEmailActiveUsePassword(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + username));
        return User.withUsername(username)
                .password(userEntity.getPassword())
                .build();
    }
}
