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

import org.ozoneplatform.service.AbstractEntityService;
import org.ozoneplatform.entity.Entity;
import org.ozoneplatform.entity.Id;

import org.ozoneplatform.dto.DtoFactory;
import org.ozoneplatform.dto.DtoFactoryFactory;
import org.ozoneplatform.dto.InDto;

/**
 * Parent class of JAX-RS resources that return collections.  This is split from
 * AbstractEntityResource so that subclasses can declare the @Produces annotation
 * at the class leve instead of having to rewrite a bunch of method signatures
 */
public abstract class AbstractEntitiesResource<T extends Entity> {

    protected AbstractEntityService<T> service;
    protected DtoFactoryFactory dtoFactoryFactory;

    /**
     * GET all of the domain objects of type T, optionally
     * with paging parameters to limit the size of the return
     */
    @GET
    public Collection<DtoFactory<T>> readAll(@Context UriInfo uriInfo,
            @QueryParam("offset") Integer offset,
            @QueryParam("max") Integer max) {
        if (offset == null) offset = 0;
        if (max == null) max = 0;

        return dtoFactoryFactory.createDtoFactoryCollection(service.getAll(offset, max),
                uriInfo.getBaseUriBuilder());
    }
}
