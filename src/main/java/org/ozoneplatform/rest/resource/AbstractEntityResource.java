package org.ozoneplatform.rest.resource;

import java.util.Collection;

import java.net.URI;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;

import org.ozoneplatform.service.AbstractEntityService;
import org.ozoneplatform.entity.Entity;
import org.ozoneplatform.entity.Id;

import org.ozoneplatform.dto.DtoFactory;
import org.ozoneplatform.dto.DtoFactoryFactory;
import org.ozoneplatform.dto.InDto;

/**
 * Parent class of jaxrs rest resources, containing
 * basic CRUD functionality.  See also AbstractEntitiesResource
 */
public abstract class AbstractEntityResource<T extends Entity> {

    protected AbstractEntityService<T> service;
    @Autowired protected DtoFactoryFactory dtoFactoryFactory;

    @POST
    public Response create(@Context UriInfo uriInfo, InDto<T> dto) {
        T created = service.create(dto.fromDto());
        URI uri = UriBuilder.fromResource(this.getClass()).path(created.getId().toString())
            .build();

        return Response
            .created(uri)
            .entity(dtoFactoryFactory.createDtoFactory(created, uriInfo.getBaseUriBuilder()))
            .build();
    }

    @GET
    @Path("/{id}")
    public DtoFactory<T> read(@Context UriInfo uriInfo, @PathParam("id") Id id) {
        return dtoFactoryFactory.createDtoFactory(service.getById(id),
                uriInfo.getBaseUriBuilder());
    }

    @PUT
    @Path("/{id}")
    public DtoFactory<T> update(@Context UriInfo uriInfo,
            @PathParam("id") Id id, InDto<T> dto) {
        return dtoFactoryFactory.createDtoFactory(service.updateById(id, dto.fromDto()),
            uriInfo.getBaseUriBuilder());
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Id id) {
        service.deleteById(id);
    }
}
