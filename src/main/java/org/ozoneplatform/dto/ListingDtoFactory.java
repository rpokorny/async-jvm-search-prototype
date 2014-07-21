package org.ozoneplatform.dto;

import java.util.Collection;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.ozoneplatform.entity.Listing;
import org.ozoneplatform.entity.Intent;

import static org.ozoneplatform.dto.IntentDtoFactory.IntentDto;

class ListingDtoFactory extends DtoFactory<Listing> {
    private Collection<DtoFactory<Intent>> intentDtoFactories;

    protected ListingDtoFactory(DtoFactoryFactory dtoFactoryFactory, Listing instance) {
        this.dtoFactoryFactory = dtoFactoryFactory;
        this.instance = instance;

        intentDtoFactories = dtoFactoryFactory.createDtoFactoryCollection(instance.getIntents());
    }

    @JsonCreator
    public ListingDtoFactory(
        @JsonProperty("title") String title,
        @JsonProperty("intents") Collection<DtoFactory<Intent>> intentFactories
    ) {
        this.instance = new Listing();
        this.instance.setTitle(title);

        intentDtoFactories = intentFactories;
        Collection<Intent> intents = new ArrayList<Intent>(intentDtoFactories.size());
        for (DtoFactory<Intent> intentFactory : intentDtoFactories) {
            intents.add(intentFactory.getInstance());
        }

        this.instance.setIntents(intents);
    }

    @Override
    public Class<Listing> getDataType() { return Listing.class; }

    @Override
    public MediaType getPrimaryMediaType() { return ListingFullOutDto.MEDIA_TYPE; }

    @Override
    public OutDto<Listing> getOutDtoForMediaType(MediaType mediaType) {
        if (mediaType.equals(ListingFullOutDto.MEDIA_TYPE)) {
            return new ListingFullOutDto(instance, intentDtoFactories);
        }
        else if (mediaType.equals(ListingSimpleOutDto.MEDIA_TYPE)) {
            return new ListingSimpleOutDto(instance);
        }
        else return super.getOutDtoForMediaType(mediaType);
    }

    static class ListingFullOutDto extends AbstractEntityDto<Listing> {
        static final MediaType MEDIA_TYPE =
            new MediaType("application", "vnd.ozp.store.listing+json");

        private Collection<DtoFactory<Intent>> intentDtoFactories;

        ListingFullOutDto(Listing listing, Collection<DtoFactory<Intent>> intentDtoFactories) {
            this.entity = listing;
            this.intentDtoFactories = intentDtoFactories;
        }

        public String getTitle() { return entity.getTitle(); }

        public Collection<OutDto<Intent>> getIntents() {
            Collection<OutDto<Intent>> retval =
                new ArrayList<OutDto<Intent>>(intentDtoFactories.size());

            for (DtoFactory<Intent> intentDtoFactory : intentDtoFactories) {
                retval.add(intentDtoFactory.getDto());
            }

            return retval;
        }
    }

    static class ListingSimpleOutDto extends AbstractEntityDto<Listing> {
        static final MediaType MEDIA_TYPE =
            new MediaType("application", "vnd.ozp.store.listing.simple+json");

        ListingSimpleOutDto(Listing listing) {
            this.entity = listing;
        }

        public String getTitle() { return entity.getTitle(); }
    }
}
