package org.identity.identityserver.repository;

import org.identity.identityserver.model.dto.IdNameDTO;
import org.identity.identityserver.model.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/27/2025
 * ResourceRepository.java
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, UUID>, JpaSpecificationExecutor<Resource> {
    @Query("SELECT r.id AS id, r.name AS name " +
            "FROM Resource r JOIN ResourceScope rs ON r.id = rs.id.resourceId " +
            "WHERE rs.id.scopeId = :scopeId")
    List<IdNameDTO> getIdNameDTOsByScopeId(@Param("scopeId") UUID scopeId);
}
