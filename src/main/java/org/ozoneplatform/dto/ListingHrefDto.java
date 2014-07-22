package org.ozoneplatform.dto;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.ozoneplatform.entity.Listing;

import org.ozoneplatform.rest.resource.ListingResource;

class ListingHrefDto extends EntityHrefDto<Listing> {
    static final MediaType MEDIA_TYPE =
        new MediaType("application", "vnd.ozp.store.listing.href+json");

    public ListingHrefDto(Listing entity, UriBuilder halUriBuilder) {
        super(entity, ListingResource.class, halUriBuilder);
    }

    public String getTitle() { return entity.getTitle(); }
}
