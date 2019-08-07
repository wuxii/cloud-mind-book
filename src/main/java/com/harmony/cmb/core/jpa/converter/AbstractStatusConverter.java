package com.harmony.cmb.core.jpa.converter;

import com.harmony.cmb.core.Status;
import org.springframework.util.ReflectionUtils;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wuxii
 */
public abstract class AbstractStatusConverter<T extends Status> implements AttributeConverter<T, String> {

    private Class<T> targetStatus;
    private final Map<String, T> mappings;

    @SuppressWarnings("unchecked")
    protected AbstractStatusConverter(Class<T> targetStatus) {
        this.targetStatus = targetStatus;
        this.mappings = Arrays
                .stream(targetStatus.getFields())
                .map(e -> (T) ReflectionUtils.getField(e, targetStatus))
                .collect(Collectors.toMap(this::toQualifyName, v -> v));
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        return attribute == null ? null : attribute.qualify();
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        return mappings.get(dbData);
    }

    public Class<T> getTargetStatusClass() {
        return targetStatus;
    }

    protected String toQualifyName(T status) {
        return status.qualify();
    }

}
