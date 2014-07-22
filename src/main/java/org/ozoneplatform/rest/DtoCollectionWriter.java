package org.ozoneplatform.rest;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
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
public class DtoCollectionWriter implements MessageBodyWriter<Collection<DtoFactory<?>>> {
    @Autowired private ObjectMapper objectMapper;

    private static final Pattern MEDIA_TYPE_PATTERN = Pattern.compile("^(.*?)s?(\\+json)?$");

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        if (Collection.class.isAssignableFrom(type)) {
            Type[] typeParams = ((ParameterizedType)genericType).getActualTypeArguments();

            Type toTest = typeParams[0];
            if (toTest instanceof ParameterizedType) {
                toTest = ((ParameterizedType)toTest).getRawType();
            }

try {
}
catch (Exception e) {
}
            return DtoFactory.class.isAssignableFrom((Class)toTest);
        }
        else {
            return false;
        }
    }

    //This method is deprecated and unused in JAX-RS 2
    @Override
    public long getSize(Collection<DtoFactory<?>> t, Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Collection<DtoFactory<?>> collection, Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String,Object> httpHeaders, OutputStream entityStream)
            throws IOException {
        Collection<OutDto<?>> dtoCollection =
            new ArrayList<OutDto<?>>(collection.size());

        MediaType baseMediaType = getBaseMediaTypeFromCollectionMediaType(mediaType);

        for (DtoFactory<?> factory : collection) {
            dtoCollection.add(factory.getOutDtoForMediaType(baseMediaType));
        }

        objectMapper.writeValue(entityStream, dtoCollection);
    }

    /**
     * Transform a media type for a collection into a media type for the elements of that
     * collection.  This is done using the simple rule that the second half of the
     * media type, not counting the '+json' part, has an extra 's' appended for the
     * collection type.  For example, if the base media type is
     * application/vnd.ozp.store.listing+json then the collection media type is
     * application/vnd.ozp.store.listings+json
     */
    private MediaType getBaseMediaTypeFromCollectionMediaType(MediaType collectionMediaType) {
        String type = collectionMediaType.getType();
        String collectionSubType = collectionMediaType.getSubtype();
        String baseSubType;

        Matcher matcher = MEDIA_TYPE_PATTERN.matcher(collectionSubType);
        matcher.find();
        if (matcher.group(2) != null) {
            baseSubType = matcher.group(1) + matcher.group(2);
        }
        else {
            baseSubType = matcher.group(1);
        }


        return new MediaType(type, baseSubType);
    }
}
