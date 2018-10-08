package com.ydrozdov.smileco.domain.model.enums;

public enum RelationshipType {
    MARRIAGE(1), DIVORCE(2), PARENTHOOD(3), UNKNOWN(-1);

    private final int val;

    RelationshipType(int val) {
        this.val = val;
    }

    public static RelationshipType fromInt(Integer val) {
        for (RelationshipType type : RelationshipType.values()) {
            if (val == type.getInt()) {
                return type;
            }
        }

        return RelationshipType.UNKNOWN;
    }

    public int getInt() {
        return val;
    }


}
