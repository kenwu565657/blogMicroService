package com.contentfarm.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public abstract class ConverterBase<T extends Enum<T> & EnumBase<E>, E> implements AttributeConverter<T, E> {
    private final Class<T> clazz;

    public ConverterBase(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public E convertToDatabaseColumn(T attribute) {
        return attribute != null ? attribute.getCodeInDb() : null;
    }

    @Override
    public T convertToEntityAttribute(E dbData) {
        T[] enums = clazz.getEnumConstants();
        for (T e : enums) {
            if (e.getCodeInDb().equals(dbData)) {
                return e;
            }
        }
        return null;
    }
}