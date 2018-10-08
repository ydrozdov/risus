package com.ydrozdov.smileco.domain.model;

import com.ydrozdov.smileco.domain.model.enums.Sex;

import java.time.LocalDate;

public class PersonModel {

    private String name;
    private LocalDate birthDate;
    private String birthPlace;
    private Sex sex;
    private RelationshipModel relationship;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public RelationshipModel getRelationship() {
        return relationship;
    }

    public void setRelationship(RelationshipModel relationship) {
        this.relationship = relationship;
    }
}
