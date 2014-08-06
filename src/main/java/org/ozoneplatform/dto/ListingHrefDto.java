package org.ozoneplatform.dto;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.ozoneplatform.entity.Listing;

class ListingHrefDto extends AbstractEntityDto<Listing> {
    static final MediaType MEDIA_TYPE =
        new MediaType("application", "vnd.ozp.store.listing.href+json");

    public ListingHrefDto(Listing entity) {
        super(entity);
    }

    public String getTitle() { return entity.getTitle(); }
}
