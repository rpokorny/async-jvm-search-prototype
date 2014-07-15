package org.ozoneplatform.exception;

import org.ozoneplatform.entity.Entity;
import org.ozoneplatform.entity.Id;

public class EntityNotFoundException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public EntityNotFoundException(Class<? extends Entity> clazz, Id id) {
        super(notFoundMessage(clazz, id));
    }

    private static String notFoundMessage(Class<? extends Entity>clazz, Id id) {
        return clazz.getSimpleName() + " with id " + id + " not found";
    }
}
