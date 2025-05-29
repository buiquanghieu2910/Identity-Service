package org.identity.identityserver.repository;

import org.identity.identityserver.model.dto.IdNameDTO;
import org.identity.identityserver.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/29/2025
 * PermissionRepository.java
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID>, JpaSpecificationExecutor<Permission> {
    @Query("SELECT p.id AS id, p.name AS name " +
            "FROM Permission p JOIN PermissionScope ps ON p.id = ps.id.permissionId " +
            "WHERE ps.id.scopeId = :scopeId")
    List<IdNameDTO> getIdNameDTOsByScopeId(@Param("scopeId") UUID scopeId);
}
