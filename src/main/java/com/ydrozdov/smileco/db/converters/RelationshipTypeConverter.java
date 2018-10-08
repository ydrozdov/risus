package com.ydrozdov.smileco.db.converters;

import com.ydrozdov.smileco.domain.model.enums.RelationshipType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RelationshipTypeConverter implements AttributeConverter<RelationshipType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(RelationshipType type) {
        return type.getInt();
    }

    @Override
    public RelationshipType convertToEntityAttribute(Integer typeInt) {
        return RelationshipType.fromInt(typeInt);
    }
}
