package org.ozoneplatform.dto;

import java.net.URI;

/**
 * A class that represents a HAL href.  This class can be extended with subclasses
 * to add other attributes
 */
public class HrefDto {
    private final URI href;

    public HrefDto(URI href) {
        if (href == null) {
            throw new NullPointerException();
        }

        this.href = href;
    }

    public URI getHref() { return href; }
}
