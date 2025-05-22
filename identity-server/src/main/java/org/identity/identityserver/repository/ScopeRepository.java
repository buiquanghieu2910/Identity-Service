package org.identity.identityserver.repository;

import org.identity.identityserver.model.entity.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/13/2025
 * ScopeRepository.java
 */
@Repository
public interface ScopeRepository extends JpaRepository<Scope, UUID> {
}
