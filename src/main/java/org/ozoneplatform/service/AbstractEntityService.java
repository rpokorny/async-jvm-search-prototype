package org.ozoneplatform.service;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.ozoneplatform.entity.Id;
import org.ozoneplatform.entity.Entity;

import org.ozoneplatform.exception.EntityNotFoundException;

abstract public class AbstractEntityService<T extends Entity> {
    private Map<Id, T> entities = new HashMap<Id, T>();

    protected final Class<T> entityClass;

    protected AbstractEntityService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T create(T entity) {
        entity.setId(new Id());
        entities.put(entity.getId(), entity);
        return entity;
    }

    public Collection<T> getAll(int offset, int max) {
        return entities.values();
    }

    public T getById(Id id) {
        T entity = entities.get(id);

        if (entity == null) {
            throw new EntityNotFoundException(entityClass, id);
        }

        return entity;
    }

    public T updateById(Id id, T entity) {
        entity.setId(id);
        entities.put(entity.getId(), entity);
        return entity;
    }

    public void deleteById(Id id) {
        entities.remove(id);
    }
}
