package org.ozoneplatform.dto;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.ozoneplatform.entity.Listing;

import org.ozoneplatform.rest.resource.ListingResource;

/**
 * Extends the ListingInDto to include HAL links for use as an output DTO
 */
class ListingDto extends ListingInDto implements InDto<Listing> {
    static final MediaType MEDIA_TYPE =
        new MediaType("application", "vnd.ozp.store.listing+json");

    ListingDto(Listing listing, UriBuilder halUriBuilder) {
        super(listing);
        this.addLink("self",
            new EntityHrefDto<Listing>(listing, ListingResource.class, halUriBuilder));
    }
}
