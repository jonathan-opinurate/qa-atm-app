package garyttierney.atmapp.database;

public class CustomerRepositoryException extends Exception {
    public CustomerRepositoryException(String message, Exception ex) {
        super(message, ex);
    }
}
