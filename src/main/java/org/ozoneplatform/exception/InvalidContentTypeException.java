package org.ozoneplatform.exception;

import javax.ws.rs.core.MediaType;

public class InvalidContentTypeException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public InvalidContentTypeException(MediaType mediaType) {
        super("Invalid Media Type " + mediaType.toString());
    }


}
