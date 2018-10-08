package com.ydrozdov.smileco.domain.specifications;

import com.ydrozdov.smileco.db.entities.RelationshipEntity;
import org.springframework.data.jpa.domain.Specification;

public abstract class BaseSpecification<T, U> {
    public abstract Specification<T> getFilter(U person1, U person2);
    public Specification<T> attributeEqualsValue(U value, String attribute) {
        return (root, query, cb) -> cb.equal(root.get(attribute), value);
    }
}
