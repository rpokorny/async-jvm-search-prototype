package org.ozoneplatform.service;

import java.util.Collection;

abstract public class AbstractService<T> {
    public T create(T entity) {
        return null;
    }

    public Collection<T> getAll(int offset, int max) {
        return null;
    }

    public T getById(long id) {
        return null;
    }

    public T updateById(long id, T entity) {
        return null;
    }

    public void deleteById(long id) {

    }
}
