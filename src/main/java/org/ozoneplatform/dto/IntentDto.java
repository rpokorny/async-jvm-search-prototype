package org.ozoneplatform.dto;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.ozoneplatform.entity.Intent;

/**
 * A DTO for intents.  This class can be used both as an input DTO and an output DTO
 */
class IntentDto extends AbstractEntityDto<Intent> implements InDto<Intent> {
    public static final MediaType MEDIA_TYPE =
        new MediaType("application", "vnd.ozp.store.intent+json");

    IntentDto(Intent intent) {
        super(intent);
    }

    @JsonCreator
    public IntentDto(
        @JsonProperty("send") boolean send,
        @JsonProperty("receive") boolean receive,
        @JsonProperty("action") String action,
        @JsonProperty("dataType") String dataType
    ) {
        super(new Intent());
        this.entity.setSend(send);
        this.entity.setReceive(receive);
        this.entity.setAction(action);
        this.entity.setDataType(dataType);
    }

    public boolean getSend() { return entity.getSend(); }
    public boolean getReceive() { return entity.getReceive(); }
    public String getAction() { return entity.getAction(); }
    public String getDataType() { return entity.getDataType(); }

    public Intent fromDto() { return entity; }
}
