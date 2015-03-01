package garyttierney.atmapp.service;

import com.google.inject.Inject;
import garyttierney.atmapp.database.CustomerRepository;
import garyttierney.atmapp.database.CustomerRepositoryException;
import garyttierney.atmapp.model.Customer;

public class CustomerBlacklistingService {

    private final CustomerRepository repository;

    @Inject
    public CustomerBlacklistingService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void blacklist(Customer customer) throws CustomerBlacklistingException {
        customer.setBlacklisted(true);
        try {
            repository.updateCustomer(customer);
        } catch (CustomerRepositoryException e) {
            throw new CustomerBlacklistingException("Failed to add customer to blacklist!", e);
        }
    }
}
