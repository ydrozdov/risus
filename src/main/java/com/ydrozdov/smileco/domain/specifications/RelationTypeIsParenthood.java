package com.ydrozdov.smileco.domain.specifications;

import com.ydrozdov.smileco.db.entities.RelationshipEntity;
import com.ydrozdov.smileco.domain.model.enums.RelationshipType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RelationTypeIsParenthood implements Specification<RelationshipEntity> {
    @Override
    public Predicate toPredicate
            (Root<RelationshipEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder){
        return builder.equal(root.get("type"), RelationshipType.PARENTHOOD);
    }
}
