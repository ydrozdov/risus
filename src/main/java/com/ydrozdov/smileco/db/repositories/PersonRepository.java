package com.ydrozdov.smileco.db.repositories;

import com.ydrozdov.smileco.db.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<PersonEntity, Integer>, JpaSpecificationExecutor<PersonEntity> {
    List<PersonEntity> findByName(String name);
}
