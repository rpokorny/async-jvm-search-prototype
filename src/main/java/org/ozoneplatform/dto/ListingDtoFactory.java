package org.ozoneplatform.dto;

import java.util.Collection;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.ozoneplatform.entity.Listing;
import org.ozoneplatform.entity.Intent;


class ListingDtoFactory extends DtoFactory<Listing> {

    protected ListingDtoFactory(Listing instance, DtoFactoryFactory dtoFactoryFactory,
            UriBuilder halUriBuilder) {
        super(instance, dtoFactoryFactory, halUriBuilder);
    }

    @Override
    public Class<Listing> getDataType() { return Listing.class; }

    @Override
    public MediaType getPrimaryMediaType() { return ListingDto.MEDIA_TYPE; }

    @Override
    public OutDto<Listing> getOutDtoForMediaType(MediaType mediaType) {
        if (mediaType.equals(ListingDto.MEDIA_TYPE)) {
            return new ListingDto(instance, halUriBuilder);
        }
        else if (mediaType.equals(ListingSimpleOutDto.MEDIA_TYPE)) {
            return new ListingSimpleOutDto(instance, halUriBuilder);
        }
        else return super.getOutDtoForMediaType(mediaType);
    }
}
