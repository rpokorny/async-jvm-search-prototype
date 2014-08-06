package org.ozoneplatform.dto;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.ozoneplatform.entity.Listing;

class ListingSimpleOutDto extends AbstractEntityDto<Listing> {
    static final MediaType MEDIA_TYPE =
        new MediaType("application", "vnd.ozp.store.listing.simple+json");

    ListingSimpleOutDto(Listing listing, UriBuilder halUriBuilder) {
        super(listing);
        //this.addLink("self",
            //new EntityHrefDto<Listing>(listing, ListingResource.class, halUriBuilder));
    }

    public String getTitle() { return entity.getTitle(); }
}
