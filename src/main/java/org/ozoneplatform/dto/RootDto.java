package org.ozoneplatform.dto;

public interface RootDto {

    /**
     * The Content-Type represented by this DTO.  Should only vary
     * per class (not per instance)
     */
    public String getContentType();
}
