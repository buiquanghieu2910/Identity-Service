package org.identity.identityserver.repository;

import org.identity.identityserver.model.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/27/2025
 * ResourceRepository.java
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, UUID>, JpaSpecificationExecutor<Resource> {
}
