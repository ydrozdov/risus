package com.ydrozdov.smileco.db.entities;

import com.ydrozdov.smileco.domain.model.enums.Sex;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Table(name = "person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message="Name cannot be empty")
    private String name;
    @Column(name="birth_date")
    private LocalDate birthDate;
    @NotEmpty
    @Column(name="birth_place")
    private String birthPlace;
    @Enumerated(EnumType.STRING)
    private Sex sex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

}
