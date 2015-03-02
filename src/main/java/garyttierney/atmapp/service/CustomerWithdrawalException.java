package garyttierney.atmapp.service;

/**
 * This class represents an exception which occurs when a user is making a withdrawal, it can be thrown because the user has insufficient funds,
 * because the amount requested exceeds their remaining daily limit, or because an unknown database error occurred. The message this exception
 * is instantiated with is the one shown to the user when they encounter this error.
 */
public class CustomerWithdrawalException extends Exception {
    /**
     * {@inheritDoc}
     */
    public CustomerWithdrawalException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public CustomerWithdrawalException(String message, Exception ex) {
        super(message, ex);
    }
}
