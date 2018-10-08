package com.ydrozdov.smileco.domain.model.enums;

public enum Role {
    PARENT(9), CHILD(10), WIFE(1), HUSBAND(2), SPOUSE(11), SIBLING(12);

    private final int val;

    Role(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static Role fromInt(final int val) {
        for (Role role : Role.values()) {
            if (val == role.getVal()) {
                return role;
            }
        }

        throw new IllegalArgumentException(String.format("Unknown role %s", val));
    }
}
