package org.identity.identityserver.util;

import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author BUI_QUANG_HIEU
 * 5/27/2025
 * Specifications.java
 */
public class Specifications<E> {

    public static <E> Specification<E> search(List<SingularAttribute<E, String>> attributes, String value) {
        if (value == null || attributes.isEmpty()) {
            return null;
        }
        return doSearch(attributes, value);
    }

    public static <E> Specification<E> doSearch(List<SingularAttribute<E, String>> attributes, String value) {
        var attr = doContain(attributes.get(0), value);
        for (SingularAttribute<E, String> attribute : attributes) {
            attr = attr.or(doContain(attribute, value));
        }
        return attr;
    }

    public static <E> Specification<E> contain(SingularAttribute<E, String> attribute, String value) {
        if (value == null || attribute == null) {
            return null;
        }
        return doContain(attribute, value);
    }

    public static <E, T> Specification<E> in(SingularAttribute<E, T> attribute, List<T> values) {
        if (CollectionUtils.isEmpty(values)) {
            return null;
        }
        return (root, query, cb) -> root.get(attribute).in(values);
    }

    public static <E, T> Specification<E> notIn(SingularAttribute<E, T> attribute, List<T> values) {
        if (CollectionUtils.isEmpty(values)) {
            return null;
        }
        return (root, query, cb) -> cb.not(root.get(attribute).in(values));
    }

    public static <E, T> Specification<E> isNull(SingularAttribute<E, T> attribute) {
        if (attribute == null) {
            return null;
        }
        return (root, query, cb) -> cb.isNull(root.get(attribute));
    }

    public static <E, T> Specification<E> isNotNull(SingularAttribute<E, T> attribute) {
        if (attribute == null) {
            return null;
        }
        return (root, query, cb) -> cb.isNotNull(root.get(attribute));
    }

    public static <E, T> Specification<E> eq(SingularAttribute<E, T> attribute, T value) {
        if (value == null || attribute == null) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get(attribute), value);
    }

    public static <E, T> Specification<E> notEq(SingularAttribute<E, T> attribute, T value) {
        if (value == null || attribute == null) {
            return null;
        }
        return (root, query, cb) -> cb.notEqual(root.get(attribute), value);
    }

    public static <E, T extends Comparable<? super T>> Specification<E> lessThan(SingularAttribute<E, T> attribute,
                                                                                 T value) {
        if (value == null || attribute == null) {
            return null;
        }
        return (root, query, cb) -> cb.lessThan(root.get(attribute), value);
    }

    public static <E, T extends Comparable<? super T>> Specification<E> lessThanOrEqualTo(SingularAttribute<E, T> attribute, T value) {
        if (value == null || attribute == null) {
            return null;
        }
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(attribute), value);
    }

    public static <E, T extends Comparable<? super T>> Specification<E> greaterThan(SingularAttribute<E, T> attribute
            , T value) {
        if (value == null || attribute == null) {
            return null;
        }
        return (root, query, cb) -> cb.greaterThan(root.get(attribute), value);
    }

    public static <E, T extends Comparable<? super T>> Specification<E> greaterThanOrEqualTo(SingularAttribute<E, T> attribute, T value) {
        if (value == null || attribute == null) {
            return null;
        }
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(attribute), value);
    }

    public static <E> Specification<E> doContain(SingularAttribute<E, String> attribute, String value) {
        return (root, query, cb) -> cb.like(cb.lower(root.get(attribute)), "%" + value.toLowerCase() + "%");
    }
}
