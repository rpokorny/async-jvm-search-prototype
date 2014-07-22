package org.ozoneplatform.dto;

import java.util.Collection;
import java.util.ArrayList;

import javax.ws.rs.core.UriBuilder;

import org.springframework.stereotype.Component;

import org.ozoneplatform.entity.Listing;

/**
 * A class that creates DtoFactories for objects.  This class is meant to be a singleton,
 * whereas many DtoFactories will exist.
 */
@Component
public class DtoFactoryFactory {

    @SuppressWarnings("unchecked")
    public <T> DtoFactory<T> createDtoFactory(T object, UriBuilder halUriBuilder) {
        Class<?> clazz = object.getClass();

        if (clazz == Listing.class)
            return (DtoFactory<T>)createListingDtoFactory((Listing)object, halUriBuilder);
        else
            throw new IllegalArgumentException("Cannot create DtoFactory for unrecognized " +
                "class " + clazz);
    }

    public <T> Collection<DtoFactory<T>> createDtoFactoryCollection(Collection<T> objects,
            UriBuilder halUriBuilder) {
        Collection<DtoFactory<T>> retval = new ArrayList<DtoFactory<T>>(objects.size());

        for(T object : objects) {
            retval.add(createDtoFactory(object, halUriBuilder));
        }

        return retval;
    }

    @SuppressWarnings("unchecked")
    public <T> Class<? extends InDto<T>> getInDtoClass(Class<T> entityType) {
        if (entityType == Listing.class)
            return (Class<? extends InDto<T>>)ListingInDto.class
                .asSubclass(InDto.class);
        else
            throw new IllegalArgumentException("No DtoFactory for class " + entityType);
    }

    private DtoFactory<Listing> createListingDtoFactory(Listing object, UriBuilder halUriBuilder) {
        return new ListingDtoFactory(object, this, halUriBuilder);
    }
}
