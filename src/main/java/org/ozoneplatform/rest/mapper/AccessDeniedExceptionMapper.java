package org.ozoneplatform.rest.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import org.springframework.security.access.AccessDeniedException;

@Provider
public class AccessDeniedExceptionMapper extends RestExceptionMapper<AccessDeniedException> {
    private static final Logger log = Logger.getLogger(AccessDeniedExceptionMapper.class);

    public AccessDeniedExceptionMapper() {
        super(Response.Status.FORBIDDEN);
    }

    protected void doLog(AccessDeniedException e) {
        //should this be info or warn?
        //TODO once auth infrastructure is in place, improve this message to include username
        log.warn("ACCESS DENIED");

        super.doLog(e);
    }
}
