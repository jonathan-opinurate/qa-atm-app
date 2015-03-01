package garyttierney.atmapp.service;

public class CustomerBlacklistingException extends Exception {
    public CustomerBlacklistingException(String message, Exception ex) {
        super(message, ex);
    }
}
