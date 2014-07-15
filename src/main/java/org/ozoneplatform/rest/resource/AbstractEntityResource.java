package org.ozoneplatform.rest;

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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.ozoneplatform.service.AbstractEntityService;
import org.ozoneplatform.entity.Entity;
import org.ozoneplatform.entity.Id;
import org.ozoneplatform.dto.OutDtoFactory;
import org.ozoneplatform.dto.OutDto;
import org.ozoneplatform.dto.InDto;

/**
 * Parent class of jaxrs rest controllers, containing
 * basic CRUD functionality
 */
public abstract class AbstractEntityResource<T extends Entity> {

    protected AbstractEntityService<T> service;

    @POST
    public Response create(T entity) {
System.err.println("creating");
        T created = service.create(entity);
        URI uri = UriBuilder.fromResource(this.getClass()).path(created.getId().toString())
            .build();
        return Response.created(uri).entity(created).build();
    }

    /**
     * GET all of the domain objects of type T, optionally
     * with paging parameters to limit the size of the return
     */
    @GET
    public Collection<T> readAll(@QueryParam("offset") Integer offset,
            @QueryParam("max") Integer max) {
System.err.println("in readAll");
System.err.println("service = " + service);
        if (offset == null) offset = 0;
        if (max == null) max = 0;

        return service.getAll(offset, max);
    }

    @GET
    @Path("/{id}")
    public T read(@PathParam("id") Id id) {
        return service.getById(id);
    }

    @PUT
    @Path("/{id}")
    public T update(@PathParam("id") Id id, T entity) {
        return service.updateById(id, entity);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Id id) {
        service.deleteById(id);
    }
}
