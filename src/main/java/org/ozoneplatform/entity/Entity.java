package org.ozoneplatform.entity;

public abstract class Entity {
    private Id id;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }
}
