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

import org.springframework.beans.factory.annotation.Autowired;

import org.ozoneplatform.service.AbstractService;
import org.ozoneplatform.entity.Entity;
import org.ozoneplatform.dto.OutDtoFactory;
import org.ozoneplatform.dto.OutDto;
import org.ozoneplatform.dto.InDto;

/**
 * Parent class of jaxrs rest controllers, containing
 * basic CRUD functionality
 */
public abstract class AbstractResource<T extends Entity> {

    @Autowired protected AbstractService<T> service;

    @POST
    Response create(T entity) {
        T created = service.create(entity);
        URI uri = UriBuilder.fromResource(this.getClass()).path(Long.toString(created.getId())).build();
        return Response.created(uri).entity(created).build();
    }

    /**
     * GET all of the domain objects of type T, optionally
     * with paging parameters to limit the size of the return
     */
    @GET
    Collection<T> readAll(@QueryParam("offset") Integer offset,
            @QueryParam("max") Integer max) {
        return service.getAll(offset, max);
    }

    @GET
    @Path("/{id}")
    T read(@PathParam("id") long id) {
        return service.getById(id);
    }

    @PUT
    @Path("/{id}")
    T update(@PathParam("id") long id, T entity) {
        return service.updateById(id, entity);
    }

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") long id) {
        service.deleteById(id);
    }
}
