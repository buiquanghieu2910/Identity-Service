package org.identity.identityserver.repository;

import org.identity.identityserver.model.dto.IdNameDTO;
import org.identity.identityserver.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/29/2025
 * RoleRepository.java
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    @Query("SELECT r.id FROM Role r " +
            "JOIN PermissionRole pr ON r.id = pr.id.roleId " +
            "WHERE pr.id.permissionId = :permissionId")
    List<UUID> getIdsByPermissionId(@Param("permissionId") UUID permissionId);

    @Query("SELECT r.id AS id, r.name AS name " +
            "FROM Role r WHERE r.applicationId = :applicationId")
    List<IdNameDTO> getIdNameDTOsByApplicationId(@Param("applicationId") UUID applicationId);
}
