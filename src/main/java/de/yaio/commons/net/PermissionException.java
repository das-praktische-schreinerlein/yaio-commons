package de.yaio.commons.net;

public class PermissionException extends Exception {
    protected Object resource = null;
    public PermissionException(final String message, final Object resource, final Exception cause) {
        super(message, cause);
        this.resource = resource;
    }

    public Object getResource() {
        return resource;
    }
}
