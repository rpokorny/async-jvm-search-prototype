package org.ozoneplatform.dto;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.ozoneplatform.exception.InvalidContentTypeException;

/**
 * A factory for creating DTOs from a particular encapsulated Entity (or other object).
 */
public abstract class DtoFactory<T> {
    protected final DtoFactoryFactory dtoFactoryFactory;

    protected final T instance;

    /**
     * A URI builder currently configured with an absolute URI
     * to the base of the REST API
     */
    protected final UriBuilder halUriBuilder;

    public DtoFactory(T instance, DtoFactoryFactory dtoFactoryFactory,
            UriBuilder halUriBuilder) {
        this.dtoFactoryFactory = dtoFactoryFactory;
        this.instance = instance;
        this.halUriBuilder = halUriBuilder;
    }

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
}
