package garyttierney.atmapp.database;

/**
 * Represents an exception which is thrown when an operation on a {@link CustomerRepository} failed.
 */
public class CustomerRepositoryException extends Exception {

    /**
     * {@inheritDoc}
     */
    public CustomerRepositoryException(String message, Exception ex) {
        super(message, ex);
    }

    /**
     * {@inheritDoc}
     */
    public CustomerRepositoryException(String message) {
        super(message);
    }
}
