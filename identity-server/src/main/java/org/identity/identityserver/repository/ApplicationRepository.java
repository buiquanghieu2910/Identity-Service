package org.identity.identityserver.repository;

import org.identity.identityserver.model.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * ApplicationRepository.java
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, UUID> {
    Optional<Application> findByClientId(String clientId);

    @Query("SELECT a.webOrigins FROM Application a")
    Set<String> findAllWebOrigins();

    @Query("SELECT COUNT (a) > 0 FROM Application a " +
            "JOIN UserEntityApplication u ON a.id = u.id.applicationId " +
            "JOIN UserEntity ue ON u.id.userEntityId = ue.id " +
            "WHERE a.clientId = :clientId AND ue.identifier = :identifier")
    Boolean checkAcceptByIdentifier(@Param("clientId") String clientId, @Param("identifier") String identifier);

    @Query("SELECT a.name FROM Application a WHERE a.clientId = :clientId")
    String findNameByClientId(@Param("clientId") String clientId);
}
