package org.ozoneplatform.dto;

import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class OutDtoContentTypeRegistry extends
        DtoContentTypeRegistry<OutDtoFactory<?,?>> {

    //use spring to find all OutDtoFactories
    @Autowired private Set<OutDtoFactory<?,?>> factories;

    @Override
    protected Set<OutDtoFactory<?,?>> findDtoFactories() {
        return factories;
    }

    /**
     * Retrieve the DTO factory that matches the type and contentType
     * @param dataType The type that the DTO should be associated with
     * @param contentType The Content-Type that the DTO maps to
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T extends HasOutDto> OutDtoFactory<T, ? extends OutDto<T>>
            getOutDtoFactory(Class<T> dataType, MediaType contentType) {
        return (OutDtoFactory<T, ? extends OutDto<T>>)
            this.getDtoFactory(dataType, contentType);
    }
}
