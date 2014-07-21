package org.ozoneplatform.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import org.ozoneplatform.entity.Entity;
import org.ozoneplatform.entity.Id;

public abstract class AbstractEntityDto<T extends Entity> implements OutDto<T> {
    protected T entity;

    @JsonSerialize(using = ToStringSerializer.class)
    public Id getId() { return entity.getId(); }
}
