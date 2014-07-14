package org.ozoneplatform.rest;

import java.lang.reflect.Type;
import java.lang.annotation.Annotation;

import java.io.InputStream;
import java.io.IOException;

import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.ozoneplatform.dto.InDtoContentTypeRegistry;
import org.ozoneplatform.dto.InDtoFactory;
import org.ozoneplatform.dto.InDto;
import org.ozoneplatform.dto.HasInDto;

@Provider
public class DtoReader implements MessageBodyReader<HasInDto> {
    @Autowired private InDtoContentTypeRegistry inDtoContentTypeRegistry;

    @Autowired private ObjectMapper objectMapper;

    @Override
    public boolean isReadable(Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        return getFactory(type, mediaType) != null;
    }

    @Override
    //JAX-RS's underspecified type signatures prevent a fully type-safe way of implementing this
    @SuppressWarnings({"unchecked"})
    public HasInDto readFrom(Class<HasInDto> type, Type genericType,
            Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String,String> httpHeaders, InputStream entityStream)
            throws IOException {

        InDtoFactory<?, ? extends InDto<? extends HasInDto>> factory =
            (InDtoFactory<?, ? extends InDto<? extends HasInDto>>)getFactory(type, mediaType);

        InDto<? extends HasInDto> dto = objectMapper.readValue(entityStream, factory.getDtoType());
        return dto.fromDto();
    }

    @SuppressWarnings("unchecked")
    private InDtoFactory<?,?> getFactory(Class<?> type, MediaType mediaType) {
        return inDtoContentTypeRegistry.getInDtoFactory((Class<? extends HasInDto>)type,
                mediaType);
    }
}

