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
System.err.println("DtoCollectionWriter isWriteable - type = " + type);
        if (Collection.class.isAssignableFrom(type)) {
System.err.println("Collection isAssignableFrom");
            Type[] typeParams = ((ParameterizedType)genericType).getActualTypeArguments();
System.err.println("typeParams = " + Arrays.toString(typeParams));

            Type toTest = typeParams[0];
            if (toTest instanceof ParameterizedType) {
                toTest = ((ParameterizedType)toTest).getRawType();
            }

try {
System.err.println("returning " + DtoFactory.class.isAssignableFrom((Class)toTest));
}
catch (Exception e) {
System.err.println("caught exception " + e);
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
System.err.println("DtoCollectionWriter writeTo");
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

System.err.println("collectionSubType = " + collectionSubType);
        Matcher matcher = MEDIA_TYPE_PATTERN.matcher(collectionSubType);
        matcher.find();
        if (matcher.group(2) != null) {
            baseSubType = matcher.group(1) + matcher.group(2);
        }
        else {
            baseSubType = matcher.group(1);
        }


System.err.println("baseSubType = " + baseSubType);
        return new MediaType(type, baseSubType);
    }
}
