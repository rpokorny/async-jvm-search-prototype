package org.ozoneplatform.dto;

import javax.annotation.PostConstruct;

import javax.ws.rs.core.MediaType;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public abstract class DtoContentTypeRegistry<F extends DtoFactory<?,?>> {


    private static class Key<T> {
        //The class of the data type that the DTO represents (not the DTO type itself)
        Class<T> dataType;
        MediaType contentType;

        public Key(Class<T> dataType, MediaType contentType) {
            if (dataType == null || contentType == null) {
                throw new NullPointerException();
            }

            this.dataType = dataType;
            this.contentType = contentType;
        }

        public boolean equals(Object other) {
            if (other instanceof Key) {
                Key<?> otherKey = (Key)other;

                return this.dataType.equals(otherKey.dataType) &&
                    this.contentType.equals(otherKey.contentType);
            }
            else return false;
        }

        public int hashCode() {
            return dataType.hashCode() ^ contentType.hashCode();
        }
    }


    private Map<Key<?>, DtoFactory<?,?>> registry;

    @PostConstruct
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void createRegistry() {
        Set<F> factories = findDtoFactories();

        Map<Key<?>, DtoFactory<?,?>> modifiableRegistry =
            new HashMap<Key<?>, DtoFactory<?,?>>();

        for(DtoFactory<?,?> factory : factories) {
            modifiableRegistry.put(new Key(factory.getDataType(),
                factory.getContentType()), factory);
        }

        registry = Collections.<Key<?>, DtoFactory<?,?>>unmodifiableMap(modifiableRegistry);
    }

    /**
     * TODO determine best way to find available DTOs
     */
    abstract protected Set<F> findDtoFactories();

    /**
     * Retrieve the DTO factory that matches the type and contentType
     * @param dataType The type that the DTO should be associated with
     * @param contentType The Content-Type that the DTO maps to
     * @return the factory that can handle these types, or null if there isn't one
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected <T> DtoFactory<T, ? extends Dto<T>> getDtoFactory(Class<T> dataType,
            MediaType contentType) {

        DtoFactory<T, ? extends Dto<T>> factory =
            (DtoFactory<T, ? extends Dto<T>>)registry.get(new Key(dataType, contentType));

        return factory;
    }
}
