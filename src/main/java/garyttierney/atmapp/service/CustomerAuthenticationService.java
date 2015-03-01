package garyttierney.atmapp.service;

import garyttierney.atmapp.model.Customer;

public interface CustomerAuthenticationService {
    public static final String INVALID_CREDENTIALS_MSG = "Invalid account number or pin.";

    Customer authenticate(String accountNumber, String pinNumber) throws CustomerAuthenticationException;
}
