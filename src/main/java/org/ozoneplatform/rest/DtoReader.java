package org.ozoneplatform.rest;

import java.lang.reflect.Type;
import java.lang.annotation.Annotation;

import java.io.InputStream;
import java.io.IOException;

import javax.ws.rs.Consumes;

import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.ozoneplatform.dto.DtoFactory;

@Provider
public class DtoReader implements MessageBodyReader<DtoFactory<?>> {
    @Autowired private ObjectMapper objectMapper;

    @Override
    public boolean isReadable(Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        try {
            type.asSubclass(DtoFactory.class);
            return true;
        }
        catch (ClassCastException e) {
            return false;
        }
    }

    @Override
    //JAX-RS's underspecified type signatures prevent a fully type-safe way of implementing this
    @SuppressWarnings("unchecked")
    public DtoFactory<?> readFrom(Class<DtoFactory<?>> type, Type genericType,
            Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String,String> httpHeaders, InputStream entityStream)
            throws IOException {
        return objectMapper.readValue(entityStream, type);
    }
}
