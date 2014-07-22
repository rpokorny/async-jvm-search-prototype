package org.ozoneplatform.rest.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.WebApplicationException;

import org.apache.log4j.Logger;

@Provider
public class WebApplicationExceptionMapper extends RestExceptionMapper<WebApplicationException> {
    private static final Logger log = Logger.getLogger(WebApplicationExceptionMapper.class);

    public WebApplicationExceptionMapper () {
        //NOTE This argument is ignored in favor of the
        //response code in the exception
        super(Response.Status.INTERNAL_SERVER_ERROR);
    }

    @Override
    public Response toResponse(WebApplicationException e) {
        Response exceptionResponse = e.getResponse();

        //log if a 5xx error
        if (isInternalError(exceptionResponse.getStatus())) {
            log.error("5xx-level WebApplicationException", e);
        }

        return Response.status(exceptionResponse.getStatus()).entity(e).build();
    }
}
