package org.ozoneplatform.entity;

import java.util.UUID;

/**
 * An opaque(-ish) id class internally represented as a UUID
 */
public class Id {
    private UUID id;

    public Id() {
        this.id = UUID.randomUUID();
    }

    public Id(String uuidString) {
        this.id = UUID.fromString(uuidString);
    }

    public boolean equals(Object other) {
        if (other instanceof Id) {
            return id.equals(((Id)other).id);
        }
        else return false;
    }

    public int hashCode() { return id.hashCode(); }
    public String toString() { return id.toString(); }
}
