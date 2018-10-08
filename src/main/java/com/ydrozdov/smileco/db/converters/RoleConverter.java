package com.ydrozdov.smileco.db.converters;

import com.ydrozdov.smileco.domain.model.enums.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RoleConverter implements AttributeConverter<Role, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Role role) {
        return role.getVal();
    }

    @Override
    public Role convertToEntityAttribute(Integer value) {
        return Role.fromInt(value);
    }
}
