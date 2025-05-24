package org.identity.identityserver.model.response.base;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/24/2025
 * CommonResponse.java
 */
@Getter
@Setter
@MappedSuperclass
public abstract class CommonResponse {
    private UUID id;
    private String createdBy;
    private OffsetDateTime createdAt;
    private String updatedBy;
    private OffsetDateTime updatedAt;
}

