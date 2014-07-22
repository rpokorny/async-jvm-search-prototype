package org.ozoneplatform.rest;

import java.util.Map;

import java.io.OutputStream;
import java.io.IOException;

import java.lang.reflect.Type;
import java.lang.annotation.Annotation;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A superclass to hold common logic from different custom MessageBodyWriters that we
 * have, other than those that handle domain objects
 */
public abstract class AbstractMessageBodyWriter<T> implements MessageBodyWriter<T> {
    @Autowired protected ObjectMapper objectMapper;

    protected Class<? super T> clazz;

    protected AbstractMessageBodyWriter(Class<? super T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public long getSize(T t, Class<?> type, Type genericType, Annotation[] annotations,
            MediaType mediaType) {
        return -1;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations,
            MediaType mediaType) {
        return clazz.isAssignableFrom(type);
    }

    @Override
    public void writeTo(T t, Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
            throws IOException {
        objectMapper.writeValue(entityStream, toBodyMap(t));
    }

    /**
     * @return a Map representing the JSON structure of the
     * response body
     */
    protected abstract Map<String, Object> toBodyMap(T t);
}
