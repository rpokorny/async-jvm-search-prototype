package org.ozoneplatform.dto;

import javax.ws.rs.core.MediaType;

import org.ozoneplatform.exception.InvalidContentTypeException;

/**
 * A factory for creating DTOs from a particular encapsulated Entity (or other object).
 * In addition to the methods here, a concrete implementation of this class must implement a
 * constructor or static method annotated with as a Jackson JsonCreator (Java has no way to
 * formally specify this requirement)
 */
public abstract class DtoFactory<T> {
    protected DtoFactoryFactory dtoFactoryFactory;

    protected T instance;

    public abstract Class<T> getDataType();
    public OutDto<T> getOutDtoForMediaType(MediaType mediaType) throws InvalidContentTypeException {
        if (mediaType.equals(MediaType.APPLICATION_JSON_TYPE)) {
            return getDto();
        }
        else throw new InvalidContentTypeException(mediaType);
    }

    public abstract MediaType getPrimaryMediaType();

    public OutDto<T> getDto() {
        return getOutDtoForMediaType(getPrimaryMediaType());
    }

    public T getInstance() {
        return instance;
    }
}
