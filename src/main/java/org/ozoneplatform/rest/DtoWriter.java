package org.ozoneplatform.rest;

import java.lang.reflect.Type;
import java.lang.annotation.Annotation;

import java.io.OutputStream;
import java.io.IOException;

import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.ozoneplatform.dto.OutDtoContentTypeRegistry;
import org.ozoneplatform.dto.OutDtoFactory;
import org.ozoneplatform.dto.OutDto;
import org.ozoneplatform.dto.HasOutDto;

@Provider
public class DtoWriter implements MessageBodyWriter<HasOutDto> {
    @Autowired private OutDtoContentTypeRegistry outDtoContentTypeRegistry;

    @Autowired private ObjectMapper objectMapper;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        return getFactory(type, mediaType) != null;
    }

    //This method is deprecated and unused in JAX-RS 2
    @Override
    public long getSize(HasOutDto t, Class<?> type, Type genericType, Annotation[] annotations,
            MediaType mediaType) {
        return -1;
    }

    @Override
    //JAX-RS's underspecified type signatures prevent a fully type-safe way of implementing this
    @SuppressWarnings({"unchecked"})
    public void writeTo(HasOutDto t, Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String,Object> httpHeaders, OutputStream entityStream)
            throws IOException {

        OutDtoFactory<HasOutDto, ?> factory =
            (OutDtoFactory<HasOutDto, ?>)getFactory(type, mediaType);

        objectMapper.writeValue(entityStream, factory.toDto(t));
    }

    @SuppressWarnings("unchecked")
    private OutDtoFactory<?,?> getFactory(Class<?> type, MediaType mediaType) {
        return outDtoContentTypeRegistry.getOutDtoFactory((Class<? extends HasOutDto>)type,
                mediaType);
    }
}
