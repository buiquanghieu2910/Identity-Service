package org.identity.identityserver.repository;

import org.identity.identityserver.model.dto.IdNameDTO;
import org.identity.identityserver.model.entity.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * ScopeRepository.java
 */
@Repository
public interface ScopeRepository extends JpaRepository<Scope, UUID> {
    List<Scope> findByApplicationId(UUID applicationId);

    @Query("SELECT s.id AS id, s.name AS name " +
            "FROM Scope s " +
            "WHERE s.applicationId = :applicationId")
    List<IdNameDTO> getIdNameDTOsByApplicationId(@Param("applicationId") UUID applicationId);

    @Query("SELECT s.id AS id, s.name AS name " +
            "FROM Scope s JOIN ResourceScope rs ON s.id = rs.id.scopeId " +
            "WHERE rs.id.resourceId = :resourceId")
    List<IdNameDTO> getIdNameDTOsByResourceId(@Param("resourceId") UUID resourceId);


    @Query("SELECT s.id FROM Scope s " +
            "JOIN ResourceScope rs ON s.id = rs.id.scopeId " +
            "WHERE rs.id.resourceId = :resourceId")
    List<UUID> getIdsByResourceId(@Param("resourceId") UUID resourceId);

    @Query("SELECT s.id FROM Scope s " +
            "JOIN PermissionScope ps ON s.id = ps.id.scopeId " +
            "WHERE ps.id.permissionId = :permissionId")
    List<UUID> getIdsByPermissionId(@Param("permissionId") UUID permissionId);
}
