package com.ydrozdov.smileco.domain.model;

import com.ydrozdov.smileco.db.entities.PersonEntity;
import com.ydrozdov.smileco.db.entities.RelationshipEntity;
import com.ydrozdov.smileco.domain.model.enums.RelationshipType;
import com.ydrozdov.smileco.domain.model.enums.Role;
import com.ydrozdov.smileco.domain.specifications.BaseSpecification;
import com.ydrozdov.smileco.domain.specifications.ChildSpecification;
import com.ydrozdov.smileco.domain.specifications.ParentSpecification;
import com.ydrozdov.smileco.domain.specifications.SpouseSpecification;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class RelationshipModel {

    private int person1;
    private int person2;
    private LocalDate startedDate;
    private LocalDate endedDate;
    private RelationshipType type;
    private Role role1;
    private Role role2;

    public static RelationshipEntity addParenthood(final PersonEntity child, final PersonEntity parent) {
        RelationshipEntity relationship = new RelationshipEntity();
        relationship.setPerson1(child);
        relationship.setPerson2(parent);
        relationship.setStartedDate(LocalDate.now());
        relationship.setType(RelationshipType.PARENTHOOD);
        relationship.setRole1(Role.CHILD);
        relationship.setRole2(Role.PARENT);

        return relationship;
    }

    public static RelationshipEntity addMarriage(final PersonEntity person1, final PersonEntity person2) {
        RelationshipEntity relationship = new RelationshipEntity();
        relationship.setPerson1(person1);
        relationship.setPerson2(person2);
        relationship.setStartedDate(LocalDate.now());
        relationship.setType(RelationshipType.MARRIAGE);
        relationship.setRole1(Role.SPOUSE);
        relationship.setRole2(Role.SPOUSE);

        return relationship;
    }

    public static Map<String, BaseSpecification<RelationshipEntity, PersonEntity>> getSpecifications()
    {
        Map<String, BaseSpecification<RelationshipEntity, PersonEntity>> specificationMap = new HashMap<>();
        specificationMap.put(Role.SPOUSE.toString(), new SpouseSpecification());
        specificationMap.put(Role.PARENT.toString(), new ParentSpecification());
        specificationMap.put(Role.CHILD.toString(), new ChildSpecification());

        return specificationMap;
    }
}
