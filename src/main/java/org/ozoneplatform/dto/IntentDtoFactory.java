package org.ozoneplatform.dto;

import java.util.Collection;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.ozoneplatform.entity.Listing;
import org.ozoneplatform.entity.Intent;

class IntentDtoFactory extends DtoFactory<Intent> {
    protected IntentDtoFactory(Intent instance, DtoFactoryFactory dtoFactoryFactory,
            UriBuilder halUriBuilder) {
        super(instance, dtoFactoryFactory, halUriBuilder);
    }

    @Override
    public Class<Intent> getDataType() { return Intent.class; }

    @Override
    public MediaType getPrimaryMediaType() { return IntentDto.MEDIA_TYPE; }

    @Override
    public OutDto<Intent> getOutDtoForMediaType(MediaType mediaType) {
        if (mediaType.equals(IntentDto.MEDIA_TYPE)) {
            return new IntentDto(instance);
        }
        else return super.getOutDtoForMediaType(mediaType);
    }
}
