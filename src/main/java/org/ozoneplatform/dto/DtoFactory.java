package org.ozoneplatform.dto;

import javax.ws.rs.core.MediaType;

/**
 * A factory for creating are particular type of DTO
 * from a particular type of input object
 */
public interface DtoFactory<T, D extends RootDto & Dto<T>> {
    MediaType getContentType();
    Class<T> getDataType();
}
