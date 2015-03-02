package garyttierney.atmapp.service;

/**
 * This exception represents an error that occurred while authenticating the customer. Such as an invalid username or password, or
 * an unknown database error. The message passed here will be the one displayed to the user when they encounter this error.
 */
public class CustomerAuthenticationException extends Exception {
    /**
     * {@inheritDoc}
     */
    public CustomerAuthenticationException(String message, Exception ex) {
        super(message, ex);
    }

    /**
     * {@inheritDoc}
     */
    public CustomerAuthenticationException(String message) {
        super(message);
    }


}
