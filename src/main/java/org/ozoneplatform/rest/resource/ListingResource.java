package org.ozoneplatform.rest.resource;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import org.ozoneplatform.entity.Listing;
import org.ozoneplatform.entity.Id;

import org.ozoneplatform.dto.DtoFactory;
import org.ozoneplatform.dto.DtoFactoryFactory;

import org.ozoneplatform.service.ListingService;

@Path("listing")
@Component
public class ListingResource extends AbstractEntityResource<Listing> {
    @Autowired
    public ListingResource(DtoFactoryFactory dtoFactoryFactory, ListingService service) {
        this.service = service;
        this.dtoFactoryFactory = dtoFactoryFactory;
    }

    @GET
    @Override
    @Produces({
        "application/vnd.ozp.store.listings+json",
        "application/vnd.ozp.store.listing.simples+json",
        "application/json"
    })
    public Collection<DtoFactory<Listing>> readAll(@Context UriInfo uriInfo,
            @QueryParam("offset") Integer offset,
            @QueryParam("max") Integer max) {
        return super.readAll(uriInfo, offset, max);
    }

    @GET
    @Override
    @Path("/{id}")
    @Produces({
        "application/vnd.ozp.store.listing+json",
        "application/vnd.ozp.store.listing.simple+json",
        "application/json"
    })
    public DtoFactory<Listing> read(@Context UriInfo uriInfo, @PathParam("id") Id id) {
        return super.read(uriInfo, id);
    }
}
