package org.identity.identityserver.repository;

import org.identity.identityserver.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/24/2025
 * UserEntityRepository.java
 */
@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {
    @Query("SELECT u FROM UserEntity u WHERE (u.username = :input OR u.email = :input) AND u.status = 'ACTIVE' " +
            "AND u.identityType = 'USER_PASSWORD'")
    Optional<UserEntity> findByUsernameOrEmailActiveUsePassword(@Param("input") String input);
}
