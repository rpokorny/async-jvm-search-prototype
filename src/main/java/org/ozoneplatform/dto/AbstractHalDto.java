package org.ozoneplatform.dto;

import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Superclass of all HAL-style DTOs
 */
public abstract class AbstractHalDto {

    private Map<String, HrefDto> links = new HashMap<String, HrefDto>();

    @JsonProperty("_links")
    public Map<String, HrefDto> getLinks() { return links; }

    protected void addLink(String rel, HrefDto hrefDto) {
        links.put(rel, hrefDto);
    }
}
