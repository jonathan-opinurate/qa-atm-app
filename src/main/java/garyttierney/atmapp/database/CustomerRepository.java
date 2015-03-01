package garyttierney.atmapp.database;

import garyttierney.atmapp.model.Customer;

import java.util.Optional;
import java.util.Set;

public interface CustomerRepository {
    void updateCustomer(Customer customer) throws CustomerRepositoryException;

    Optional<Customer> findCustomer(String accountNumber, String pinNumber) throws CustomerRepositoryException;

    Set<Customer> listCustomers() throws CustomerRepositoryException;
}
