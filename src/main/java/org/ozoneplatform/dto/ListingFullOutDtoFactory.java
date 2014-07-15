package org.ozoneplatform.dto;

import java.util.Collection;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import org.ozoneplatform.entity.Id;
import org.ozoneplatform.entity.Listing;
import org.ozoneplatform.entity.Intent;

@Component
public class ListingFullOutDtoFactory implements OutDtoFactory<Listing, ListingFullOutDto> {
    public static final MediaType CONTENT_TYPE =
        new MediaType("application", "vnd.ozp.store.listing");

    private static final Class<Listing> DATA_TYPE = Listing.class;

    public ListingFullOutDto toDto(Listing entity) {
        return new ListingFullOutDto(entity);
    }

    public MediaType getContentType() { return CONTENT_TYPE; }
    public Class<Listing> getDataType() { return DATA_TYPE; }
}

class ListingFullOutDto implements OutDto<Listing> {
    private Listing entity;

    ListingFullOutDto(Listing entity) {
        this.entity = entity;
    }

    @JsonSerialize(using = ToStringSerializer.class)
    public Id getId() { return entity.getId(); }

    public String getTitle() { return entity.getTitle(); }
    public Collection<IntentDto> getIntents() {
        Collection<Intent> intents = entity.getIntents();
        Collection<IntentDto> retval = new ArrayList<IntentDto>(intents.size());
        for (Intent intent : intents) {
            retval.add(new IntentDto(intent));
        }

        return retval;
    }
}
