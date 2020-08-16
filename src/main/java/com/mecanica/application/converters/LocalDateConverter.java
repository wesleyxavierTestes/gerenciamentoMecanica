package com.mecanica.application.converters;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class LocalDateConverter implements AttributeConverter<LocalDateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime d){
        return d !=null ? Timestamp.valueOf(d): null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp d){
        return d !=null ? d.toLocalDateTime() : null;
    }

}