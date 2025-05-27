package org.identity.identityserver.model.filter;

import org.identity.identityserver.model.entity.Resource;
import org.identity.identityserver.model.entity.Resource_;
import org.identity.identityserver.util.Specifications;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author BUI_QUANG_HIEU
 * 5/27/2025
 * Filters.java
 */
public class Filters {
    public static Specification<Resource> toSpecification(ResourceFilter filter) {
        return Specification.where(Specifications.eq(Resource_.applicationId, filter.getApplicationId()));
    }
}
