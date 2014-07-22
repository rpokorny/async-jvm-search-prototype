package org.ozoneplatform.rest.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.ozoneplatform.exception.EntityNotFoundException;

@Provider
class EntityNotFoundExceptionMapper extends RestExceptionMapper<EntityNotFoundException> {
    public EntityNotFoundExceptionMapper() {
        super(Response.Status.NOT_FOUND);
    }
}
