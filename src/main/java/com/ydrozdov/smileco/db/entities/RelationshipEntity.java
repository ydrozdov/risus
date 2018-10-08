package com.ydrozdov.smileco.db.entities;

import com.ydrozdov.smileco.db.converters.RelationshipTypeConverter;
import com.ydrozdov.smileco.db.converters.RoleConverter;
import com.ydrozdov.smileco.domain.model.enums.RelationshipType;
import com.ydrozdov.smileco.domain.model.enums.Role;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name = "relationship")
public class RelationshipEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="person1_id")
    private PersonEntity person1;
    @ManyToOne
    @JoinColumn(name="person2_id")
    private PersonEntity person2;
    @Column(name="date_relationship_started")
    private LocalDate startedDate;
    @Column(name="date_relationship_ended")
    private LocalDate endedDate;
    @Column(name="relationship_type_id")
    @Convert(converter = RelationshipTypeConverter.class)
    private RelationshipType type;
    @Column(name="person1_role")
    @Convert(converter = RoleConverter.class)
    private Role role1;
    @Column(name="person2_role")
    @Convert(converter = RoleConverter.class)
    private Role role2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersonEntity getPerson1() {
        return person1;
    }

    public void setPerson1(PersonEntity person1) {
        this.person1 = person1;
    }

    public PersonEntity getPerson2() {
        return person2;
    }

    public void setPerson2(PersonEntity person2) {
        this.person2 = person2;
    }

    public LocalDate getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(LocalDate startedDate) {
        this.startedDate = startedDate;
    }

    public LocalDate getEndedDate() {
        return endedDate;
    }

    public void setEndedDate(LocalDate endedDate) {
        this.endedDate = endedDate;
    }

    public RelationshipType getType() {
        return type;
    }

    public void setType(RelationshipType type) {
        this.type = type;
    }

    public Role getRole1() {
        return role1;
    }

    public void setRole1(Role role1) {
        this.role1 = role1;
    }

    public Role getRole2() {
        return role2;
    }

    public void setRole2(Role role2) {
        this.role2 = role2;
    }
}
