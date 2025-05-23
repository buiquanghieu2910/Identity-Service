package org.identity.identityserver.repository;

import org.identity.identityserver.model.entity.OAuthClientRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author BUI_QUANG_HIEU
 * 5/23/2025
 * OAuthClientRegistrationRepository.java
 */
@Repository
public interface OAuthClientRegistrationRepository extends JpaRepository<OAuthClientRegistration, Long> {
    Optional<OAuthClientRegistration> findByRegistrationId(String registrationId);
}
