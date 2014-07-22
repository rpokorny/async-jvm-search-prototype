package org.ozoneplatform.dto;

import java.util.Collection;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.ozoneplatform.entity.Listing;
import org.ozoneplatform.entity.Intent;

import org.ozoneplatform.rest.resource.ListingResource;

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

        if (intentDtoFactories != null) {
            Collection<Intent> intents = new ArrayList<Intent>(intentDtoFactories.size());

            for (DtoFactory<Intent> intentFactory : intentDtoFactories) {
                this.instance.getIntents().add(intentFactory.getInstance());
            }
        }
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

    static class ListingFullOutDto extends ListingSimpleOutDto {
        static final MediaType MEDIA_TYPE =
            new MediaType("application", "vnd.ozp.store.listing+json");

        private Collection<DtoFactory<Intent>> intentDtoFactories;

        ListingFullOutDto(Listing listing, Collection<DtoFactory<Intent>> intentDtoFactories) {
            super(listing);
            this.intentDtoFactories = intentDtoFactories;
        }

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
            super(listing);
            this.addLink("self", new EntityHrefDto<Listing>(listing, ListingResource.class));
        }

        public String getTitle() { return entity.getTitle(); }
    }

    static class ListingHrefDto extends EntityHrefDto<Listing> {
        static final MediaType MEDIA_TYPE =
            new MediaType("application", "vnd.ozp.store.listing.href+json");

        public ListingHrefDto(Listing entity) {
            super(entity, ListingResource.class);
        }

        public String getTitle() { return entity.getTitle(); }
    }
}
