package org.ozoneplatform.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import org.ozoneplatform.entity.Entity;
import org.ozoneplatform.entity.Id;

public abstract class AbstractEntityDto<T extends Entity> extends AbstractHalDto
        implements OutDto<T> {
    protected final T entity;

    public AbstractEntityDto(T entity) {
        this.entity = entity;
    }

    @JsonSerialize(using = ToStringSerializer.class)
    public Id getId() { return entity.getId(); }
}
