package org.ozoneplatform.rest;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import org.ozoneplatform.entity.Listing;
import org.ozoneplatform.entity.Id;

import org.ozoneplatform.service.ListingService;

import org.ozoneplatform.dto.ListingFullOutDtoFactory;

@Path("listing")
@Component
public class ListingResource extends AbstractEntityResource<Listing> {
    @Autowired
    public ListingResource(ListingService service) {
System.err.println("!!!!!!!!!!!! service = " + service);
        this.service = service;
    }

    @GET
    @Override
    @Produces("application/vnd.ozp.store.listings")
    public Collection<Listing> readAll(@QueryParam("offset") Integer offset,
            @QueryParam("max") Integer max) {
        return super.readAll(offset, max);
    }

    @GET
    @Override
    @Path("/{id}")
    @Produces("application/vnd.ozp.store.listing")
    public Listing read(@PathParam("id") Id id) {
System.err.println("in read for id " + id);
        return service.getById(id);
    }
}
