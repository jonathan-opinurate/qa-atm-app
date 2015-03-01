package garyttierney.atmapp.service;

import com.google.inject.Inject;
import garyttierney.atmapp.database.CustomerRepository;
import garyttierney.atmapp.database.CustomerRepositoryException;
import garyttierney.atmapp.model.Customer;

import java.util.Optional;

public class CustomerAuthenticationService {
    public static final String INVALID_CREDENTIALS_MSG = "Invalid account number or pin.";

    private final CustomerRepository repository;

    @Inject
    public CustomerAuthenticationService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer authenticate(String accountNumber, String pinNumber) throws CustomerAuthenticationException {
        try {
            Optional<Customer> customer = repository.findCustomer(accountNumber, pinNumber);

            if (customer.isPresent()) {
                return customer.get();
            } else {
                throw new CustomerAuthenticationException(INVALID_CREDENTIALS_MSG);
            }

        } catch (CustomerRepositoryException ex) {
            throw new CustomerAuthenticationException("An unknown error occurred while logging in. Please try again later.", ex);
        }
    }
}
