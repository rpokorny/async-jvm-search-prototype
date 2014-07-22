package org.ozoneplatform.rest.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import javax.validation.ValidationException;

@Provider
public class ValidationExceptionMapper extends RestExceptionMapper<ValidationException> {
    public ValidationExceptionMapper() {
        super(Response.Status.BAD_REQUEST);
    }
}
