package com.ydrozdov.smileco.domain.specifications;

import com.ydrozdov.smileco.db.entities.PersonEntity;
import com.ydrozdov.smileco.db.entities.RelationshipEntity;
import org.springframework.data.jpa.domain.Specification;

import static org.springframework.data.jpa.domain.Specification.where;

public class ParentSpecification extends BaseSpecification<RelationshipEntity, PersonEntity> {
    public Specification<RelationshipEntity> getFilter(PersonEntity person1, PersonEntity person2){
        return
                where(
                        where(attributeEqualsValue(person1, "person1")
                                .and(attributeEqualsValue(person2, "person2"))
                                .and(new Person2RoleIsParentSpecififcation())
                        )
                                .or(where(attributeEqualsValue(person2, "person1")
                                        .and(attributeEqualsValue(person1, "person2"))
                                        .and(new Person2RoleIsChildSpecification())
                                )))
                        .and(new RelationTypeIsParenthood());
    }
}
