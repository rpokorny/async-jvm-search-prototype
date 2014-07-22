package org.ozoneplatform.rest;

import java.util.Map;
import java.util.HashMap;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;

/**
 * Superclass for writers of throwables.
 */
@Provider
//NOTE: because we have to return an Accept-able content type, the definitions of all
//of our custom content types must include the possibility of being an error message instead
@Produces({"application/*"})
public class ThrowableWriterSupport<T extends Throwable> extends AbstractMessageBodyWriter<T> {
    public ThrowableWriterSupport() {
        super(Throwable.class);
    }

    @Override
    protected Map<String, Object> toBodyMap(T exception) {
        String message = exception.getMessage();
        if (message == null && exception.getCause() != null)
            message = exception.getCause().getMessage();
        if (message == null)
            message = exception.getClass().getName();

        Map<String, Object> retval = new HashMap<String, Object>();
        retval.put("error", Boolean.TRUE);
        retval.put("message", message);

        return retval;
    }
}
