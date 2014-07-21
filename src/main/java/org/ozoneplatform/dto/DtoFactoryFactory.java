package org.ozoneplatform.dto;

import java.util.Collection;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import org.ozoneplatform.entity.Listing;

/**
 * A class that creates DtoFactories for objects.  This class is meant to be a singleton,
 * whereas many DtoFactories will exist.
 */
@Component
public class DtoFactoryFactory {

    @SuppressWarnings("unchecked")
    public <T> DtoFactory<T> createDtoFactory(T object) {
        Class<?> clazz = object.getClass();

        if (clazz == Listing.class)
            return (DtoFactory<T>)createListingDtoFactory((Listing)object);
        else throw new IllegalArgumentException("Cannot create DtoFactory for unrecognized " +
                "class " + clazz);
    }

    public <T> Collection<DtoFactory<T>> createDtoFactoryCollection(Collection<T> objects) {
        Collection<DtoFactory<T>> retval = new ArrayList<DtoFactory<T>>(objects.size());

        for(T object : objects) {
            retval.add(createDtoFactory(object));
        }

        return retval;
    }

    private DtoFactory<Listing> createListingDtoFactory(Listing object) {
        return new ListingDtoFactory(this, object);
    }
}
