package org.ozoneplatform.dto;

import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class InDtoContentTypeRegistry extends
        DtoContentTypeRegistry<InDtoFactory<?,?>> {

    //use spring to find all OutDtoFactories
    @Autowired private Set<InDtoFactory<?,?>> factories;

    @Override
    protected Set<InDtoFactory<?,?>> findDtoFactories() {
        return factories;
    }

    /**
     * Retrieve the DTO factory that matches the type and contentType
     * @param dataType The type that the DTO should be associated with
     * @param contentType The Content-Type that the DTO maps to
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T> InDtoFactory<T, ? extends InDto<T>>
            getInDtoFactory(Class<T> dataType, MediaType contentType) {
        return (InDtoFactory<T, ? extends InDto<T>>)
            this.getDtoFactory(dataType, contentType);
    }
}

