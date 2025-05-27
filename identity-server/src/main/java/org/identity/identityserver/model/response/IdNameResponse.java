package org.identity.identityserver.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

/**
 * @author BUI_QUANG_HIEU
 * 5/28/2025
 * IdNameResponse.java
 */
@Getter
@Setter
@Accessors(chain = true)
public class IdNameResponse {
    private UUID id;
    private String name;
}
