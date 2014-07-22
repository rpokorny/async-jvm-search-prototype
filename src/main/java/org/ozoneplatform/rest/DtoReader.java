package org.ozoneplatform.rest;

import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
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
import com.fasterxml.jackson.databind.JavaType;

import org.ozoneplatform.dto.DtoFactory;
import org.ozoneplatform.dto.DtoFactoryFactory;

@Provider
public class DtoReader implements MessageBodyReader<DtoFactory<?>> {
    @Autowired private ObjectMapper objectMapper;
    @Autowired private DtoFactoryFactory dtoFactoryFactory;

    @Override
    public boolean isReadable(Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        return DtoFactory.class.isAssignableFrom(type);
    }

    @Override
    public DtoFactory<?> readFrom(Class<DtoFactory<?>> type, Type genericType,
            Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String,String> httpHeaders, InputStream entityStream)
            throws IOException {
        ParameterizedType factoryType = (ParameterizedType)genericType;
        Class<?> entityType = (Class<?>)factoryType.getActualTypeArguments()[0];

        return objectMapper.readValue(entityStream,
                dtoFactoryFactory.getDtoFactoryClass(entityType));
    }
}
