package org.ozoneplatform.dto;

import javax.ws.rs.core.UriBuilder;

import org.ozoneplatform.entity.Entity;

public class EntityHrefDto<T extends Entity> extends HrefDto implements OutDto<T> {
    protected final T entity;

    /**
     * @param entity The entity referenced by this Href
     * @param resourceClass The JAX-RS resource class responsible for this entity
     */
    public EntityHrefDto(T entity, Class<?> resourceClass) {
        super(UriBuilder.fromResource(resourceClass)
            .path(entity.getId().toString()).build());

        this.entity = entity;
    }
}
