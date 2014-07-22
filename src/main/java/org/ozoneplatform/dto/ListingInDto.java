package org.ozoneplatform.dto;

import java.util.Collection;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.ozoneplatform.entity.Listing;
import org.ozoneplatform.entity.Intent;

class ListingInDto extends AbstractEntityDto<Listing> implements InDto<Listing> {
    ListingInDto(Listing listing) {
        super(listing);
    }

    @JsonCreator
    public ListingInDto(
        @JsonProperty("title") String title,
        @JsonProperty("intents") Collection<IntentDto> intentDtos
    ) {
        super(new Listing());
        this.entity.setTitle(title);

        if (intentDtos != null) {
            Collection<Intent> intents = new ArrayList<Intent>(intentDtos.size());

            for (IntentDto intentDto : intentDtos) {
                this.entity.getIntents().add(intentDto.fromDto());
            }
        }
    }

    public Collection<OutDto<Intent>> getIntents() {
        Collection<Intent> intents = entity.getIntents();
        Collection<OutDto<Intent>> intentDtos =
            new ArrayList<OutDto<Intent>>(intents.size());

        for (Intent intent : intents) {
            intentDtos.add(new IntentDto(intent));
        }

        return intentDtos;
    }

    public String getTitle() { return entity.getTitle(); }

    public Listing fromDto() { return entity; }
}
