package org.ozoneplatform.entity;

import java.util.Collection;

import javax.ws.rs.core.MediaType;

import org.ozoneplatform.dto.HasOutDto;
import org.ozoneplatform.dto.HasInDto;
import org.ozoneplatform.dto.ListingFullOutDtoFactory;
import org.ozoneplatform.dto.ListingInDtoFactory;

/**
 * A listing in the Store
 */
public class Listing extends Entity implements HasOutDto, HasInDto {
    private String title;
    private Collection<Intent> intents;

    public String getTitle() { return title; }
    public Collection<Intent> getIntents() { return intents; }

    public void setTitle(String title) { this.title = title; }
    public void setIntents(Collection<Intent> intents) { this.intents = intents; }

    public MediaType getDefaultOutContentType() { return ListingFullOutDtoFactory.CONTENT_TYPE; }
    public MediaType getDefaultInContentType() { return ListingInDtoFactory.CONTENT_TYPE; }
}
