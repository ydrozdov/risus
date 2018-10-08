package com.ydrozdov.smileco.services;

import com.ydrozdov.smileco.db.entities.PersonEntity;
import com.ydrozdov.smileco.db.entities.RelationshipEntity;
import com.ydrozdov.smileco.db.repositories.PersonRepository;
import com.ydrozdov.smileco.db.repositories.RelationshipRepository;
import com.ydrozdov.smileco.domain.model.RelationshipModel;
import com.ydrozdov.smileco.domain.model.enums.RelationshipType;
import com.ydrozdov.smileco.domain.model.enums.Role;
import com.ydrozdov.smileco.domain.specifications.BaseSpecification;
import com.ydrozdov.smileco.domain.specifications.ChildSpecification;
import com.ydrozdov.smileco.domain.specifications.MaleSpecification;
import com.ydrozdov.smileco.domain.specifications.ParentSpecification;
import com.ydrozdov.smileco.domain.specifications.RelationshipTypeIsMarriageSpecification;
import com.ydrozdov.smileco.domain.specifications.SpouseSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;


@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RelationshipRepository relationshipRepository;

    public Iterable<PersonEntity> findAllByName(final String name) {
        return personRepository.findByName(name);
    }

    public Optional<PersonEntity> findById(final int id) {
        return personRepository.findById(id);
    }

    public int addPerson(PersonEntity personEntity) {
        return personRepository.save(personEntity).getId();
    }

    public int addParenthood(final int childId, final int parentId) {
        Optional<PersonEntity> child = personRepository.findById(childId);
        Optional<PersonEntity> parent = personRepository.findById(parentId);
        if (child.isPresent() && parent.isPresent()) {
            RelationshipEntity relationshipEntity = RelationshipModel.addParenthood(
                    child.get(), parent.get()
            );

            return relationshipRepository.save(relationshipEntity).getId();
        }

        return 0;
    }

    public int addMarriage(final int person1Id, final int person2Id) {
        if (!relationshipRepository.isMarried(person1Id, person2Id, RelationshipType.MARRIAGE)) {
            Optional<PersonEntity> person1 = personRepository.findById(person1Id);
            Optional<PersonEntity> person2 = personRepository.findById(person2Id);
            if (person1.isPresent() && person2.isPresent()) {
                RelationshipEntity relationshipEntity = RelationshipModel.addMarriage(
                        person1.get(), person2.get()
                );

                return relationshipRepository.save(relationshipEntity).getId();
            }

            return 0;
        }

        return 0;
    }

    public int endMarriage(final int id) {
        return relationshipRepository.endMarriage(id, RelationshipType.MARRIAGE);
    }

    public ArrayList<String> getRelationships(int person1id, int person2id) {
        Optional<PersonEntity> person1 = personRepository.findById(person1id);
        Optional<PersonEntity> person2 = personRepository.findById(person2id);
        ArrayList<String> result = new ArrayList<>();
        if (person1.isPresent() && person2.isPresent()) {
            Map<String, BaseSpecification<RelationshipEntity, PersonEntity>> specificationMap = RelationshipModel.getSpecifications();
            for (Map.Entry<String, BaseSpecification<RelationshipEntity, PersonEntity>> entry : specificationMap.entrySet()) {
                Optional<RelationshipEntity> relationshipEntity = relationshipRepository.findOne(
                        entry.getValue().getFilter(person1.get(), person2.get())
                );
                if (relationshipEntity.isPresent()) {
                    if (entry.getKey() == Role.SPOUSE.toString()) {
                        result.add(entry.getKey() + ":" + relationshipEntity.get().getId());
                    } else {
                        result.add(entry.getKey());
                    }
                }
            }
        }

        return result;
    }
}
