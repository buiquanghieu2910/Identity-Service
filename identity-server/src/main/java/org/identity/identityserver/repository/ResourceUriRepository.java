package org.identity.identityserver.repository;

import org.identity.identityserver.model.entity.ResourceUri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/27/2025
 * ResourceUriRepository.java
 */
@Repository
public interface ResourceUriRepository extends JpaRepository<ResourceUri, UUID> {
    @Query("SELECT uri FROM ResourceUri WHERE resourceId = :resourceId")
    Set<String> getUrisByResourceId(@Param("resourceId") UUID resourceId);
}
