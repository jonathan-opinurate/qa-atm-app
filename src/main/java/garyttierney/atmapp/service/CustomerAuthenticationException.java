package garyttierney.atmapp.service;

public class CustomerAuthenticationException extends Exception {
    public CustomerAuthenticationException(String message, Exception ex) {
        super(message, ex);
    }
    public CustomerAuthenticationException(String message) {
        super(message);
    }
}
