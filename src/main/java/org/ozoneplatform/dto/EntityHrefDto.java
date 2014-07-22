package org.ozoneplatform.dto;

import javax.ws.rs.core.UriBuilder;

import org.ozoneplatform.entity.Entity;

public class EntityHrefDto<T extends Entity> extends HrefDto implements OutDto<T> {
    protected final T entity;

    /**
     * @param entity The entity referenced by this Href
     * @param resourceClass The JAX-RS resource class responsible for this entity
     * @param halUriBuilder A URI builder currently configured with an absolute URI
     * to the base of the REST API
     */
    public EntityHrefDto(T entity, Class<?> resourceClass, UriBuilder halUriBuilder) {
        super(halUriBuilder.path(resourceClass).path(entity.getId().toString()).build());

        this.entity = entity;
    }
}
