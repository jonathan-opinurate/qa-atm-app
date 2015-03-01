package garyttierney.atmapp.service;

public class CustomerWithdrawalException extends Exception {
    public CustomerWithdrawalException(String message) {
        super(message);
    }

    public CustomerWithdrawalException(String message, Exception ex) {
        super(message, ex);
    }
}
