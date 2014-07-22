package org.ozoneplatform.entity;

import java.util.Collection;
import java.util.ArrayList;

/**
 * A listing in the Store
 */
public class Listing extends Entity {
    private String title;
    private Collection<Intent> intents = new ArrayList<Intent>();

    public String getTitle() { return title; }
    public Collection<Intent> getIntents() { return intents; }

    public void setTitle(String title) { this.title = title; }
    public void setIntents(Collection<Intent> intents) { this.intents = intents; }
}
