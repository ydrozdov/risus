package com.ydrozdov.smileco.domain.model.dto;

import javax.validation.constraints.Positive;

public class CoupleDTO {

    @Positive
    private int person1Id;

    @Positive
    private int person2Id;

    public int getPerson1Id() {
        return person1Id;
    }

    public void setPerson1Id(int person1Id) {
        this.person1Id = person1Id;
    }

    public int getPerson2Id() {
        return person2Id;
    }

    public void setPerson2Id(int person2Id) {
        this.person2Id = person2Id;
    }
}
