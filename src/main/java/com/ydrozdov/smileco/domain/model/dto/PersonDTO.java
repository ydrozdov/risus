package com.ydrozdov.smileco.domain.model.dto;

import com.ydrozdov.smileco.domain.model.enums.Sex;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class PersonDTO {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotEmpty(message = "birthPlace cannot be empty")
    private String birthPlace;

    private Sex sex;

    private int[] parentIds = new int[2];

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

    public int[] getParentIds() {
        return parentIds;
    }

    public void setParentIds(int[] parentIds) {
        this.parentIds = parentIds;
    }

    public int getFirstParentId() {
        return this.parentIds[0];
    }

    public int getSecondParentId() {
        return this.parentIds[1];
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
