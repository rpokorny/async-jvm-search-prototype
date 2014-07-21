package org.ozoneplatform.rest;

import java.lang.reflect.Type;
import java.lang.annotation.Annotation;

import java.io.OutputStream;
import java.io.IOException;

import javax.ws.rs.Produces;

import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.ozoneplatform.dto.DtoFactory;
import org.ozoneplatform.dto.OutDto;

@Provider
public class DtoWriter implements MessageBodyWriter<DtoFactory<?>> {
    @Autowired private ObjectMapper objectMapper;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        //not much we can do here without the factory instance...
        try {
            type.asSubclass(DtoFactory.class);
            return true;
        }
        catch (ClassCastException e) {
            return false;
        }
    }

    //This method is deprecated and unused in JAX-RS 2
    @Override
    public long getSize(DtoFactory<?> t, Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(DtoFactory<?> factory, Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String,Object> httpHeaders, OutputStream entityStream)
            throws IOException {

        objectMapper.writeValue(entityStream, factory.getOutDtoForMediaType(mediaType));
    }
}
