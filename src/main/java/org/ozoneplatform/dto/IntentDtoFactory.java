package org.ozoneplatform.dto;

import java.util.Collection;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.ozoneplatform.entity.Listing;
import org.ozoneplatform.entity.Intent;

class IntentDtoFactory extends DtoFactory<Intent> {
    protected IntentDtoFactory(DtoFactoryFactory dtoFactoryFactory, Intent instance) {
        this.dtoFactoryFactory = dtoFactoryFactory;
        this.instance = instance;
    }

    @JsonCreator
    public IntentDtoFactory(
        @JsonProperty("send") boolean send,
        @JsonProperty("receive") boolean receive,
        @JsonProperty("action") String action,
        @JsonProperty("dataType") String dataType
    ) {
        this.instance = new Intent();
        this.instance.setSend(send);
        this.instance.setReceive(receive);
        this.instance.setAction(action);
        this.instance.setDataType(dataType);
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

    static class IntentDto extends AbstractEntityDto<Intent> {
        static final MediaType MEDIA_TYPE =
            new MediaType("application", "vnd.ozp.store.intent+json");

        IntentDto(Intent intent) {
            this.entity = intent;
        }

        public boolean getSend() { return entity.getSend(); }
        public boolean getReceive() { return entity.getReceive(); }
        public String getAction() { return entity.getAction(); }
        public String getDataType() { return entity.getDataType(); }

    }
}
