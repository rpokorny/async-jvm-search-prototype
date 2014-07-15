package org.ozoneplatform.dto;

import java.util.Collection;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.ozoneplatform.entity.Listing;
import org.ozoneplatform.entity.Intent;
import org.ozoneplatform.entity.Id;

@Component
public class ListingInDtoFactory implements InDtoFactory<Listing, ListingInDto> {
    public static final MediaType CONTENT_TYPE =
        new MediaType("application", "vnd.ozp.store.listing.in");

    private static final Class<Listing> DATA_TYPE = Listing.class;
    private static final Class<ListingInDto> DTO_TYPE = ListingInDto.class;

    public Class<ListingInDto> getDtoType() { return DTO_TYPE; }

    public MediaType getContentType() { return CONTENT_TYPE; }
    public Class<Listing> getDataType() { return DATA_TYPE; }
}

class ListingInDto implements InDto<Listing> {
    private Listing entity;

    @JsonCreator
    ListingInDto(@JsonProperty("id") Id id, @JsonProperty("title") String title,
            @JsonProperty("intents") Collection<IntentDto> intents) {
        entity = new Listing();
        entity.setId(id);
        entity.setTitle(title);

        Collection<Intent> intentsEntities = new ArrayList<Intent>(intents.size());
        for (IntentDto dto : intents) {
            intentsEntities.add(dto.fromDto());
        }

        entity.setIntents(intentsEntities);
    }

    public Listing fromDto() { return entity; }
}
