package org.identity.identityserver.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author BUI_QUANG_HIEU
 * 5/16/2025
 * SetToStringConverter.java
 */
@Converter
public class SetToStringConverter implements AttributeConverter<Set<String>, String> {

    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(Set<String> list) {
        return list != null ? String.join(SPLIT_CHAR, list) : "";
    }

    @Override
    public Set<String> convertToEntityAttribute(String joined) {
        return (joined != null && !joined.isBlank())
                ? Arrays.stream(joined.split(SPLIT_CHAR))
                .map(String::trim)
                .collect(Collectors.toSet())
                : Collections.emptySet();
    }
}
