package com.ydrozdov.smileco.db.repositories;

import com.ydrozdov.smileco.db.entities.RelationshipEntity;
import com.ydrozdov.smileco.domain.model.enums.RelationshipType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface RelationshipRepository extends CrudRepository<RelationshipEntity, Integer>, JpaSpecificationExecutor<RelationshipEntity> {

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM RelationshipEntity r " +
            "WHERE r.person1 = :person1Id and r.person2=:person2Id and r.type=:type and r.endedDate is null")
    boolean isMarried(@Param("person1Id") int person1Id, @Param("person2Id") int person2Id,
                      @Param("type") RelationshipType type);
    @Modifying
    @Transactional
    @Query("UPDATE RelationshipEntity r SET r.endedDate=current_date " +
            "WHERE r.id=:id AND r.endedDate is null and r.type=:type")
    int endMarriage(@Param("id") int id, @Param("type") RelationshipType type);
}
