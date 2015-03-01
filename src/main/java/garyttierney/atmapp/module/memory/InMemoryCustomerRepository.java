package garyttierney.atmapp.module.memory;

import garyttierney.atmapp.database.CustomerRepository;
import garyttierney.atmapp.database.CustomerRepositoryException;
import garyttierney.atmapp.model.Customer;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class InMemoryCustomerRepository implements CustomerRepository {
    private final Set<Customer> customers;

    public InMemoryCustomerRepository(Set<Customer> customers) {
        this.customers = Collections.unmodifiableSet(customers);
    }

    /**
     * Update a customers withdrawalLimit, balance and blacklist flag in the database.
     *
     * @param newCustomer The customer to update.
     * @throws garyttierney.atmapp.database.CustomerRepositoryException If an error occurred while executing the update.
     */
    @Override
    public void updateCustomer(Customer newCustomer) throws CustomerRepositoryException {
        // do nothing - customer is updated in memory automatically by the CustomerWithdrawalService
    }

    /**
     * Lookup a customer by their accountNumber and pinNumber and return an Optional object with the result of the query.
     *
     * @param accountNumber The account number (username) of the customer.
     * @param pinNumber     The pin number (password) of the customer.
     * @return An {@link java.util.Optional} object either empty, if no customer was found, or with the found customer attached.
     * @throws garyttierney.atmapp.database.CustomerRepositoryException If an error occurred while performing the lookup.
     */
    @Override
    public Optional<Customer> findCustomer(String accountNumber, String pinNumber) throws CustomerRepositoryException {
        return customers.stream()
                .filter((customer) -> accountNumber.equals(customer.getAccountNumber()) && pinNumber.equals(customer.getPinNumber()))
                .findFirst();
    }

    /**
     * Lookup all customers in the database.
     *
     * @return All of the customers available in the database.
     * @throws garyttierney.atmapp.database.CustomerRepositoryException If an error occurred while performing the lookup.
     */
    @Override
    public Set<Customer> listCustomers() throws CustomerRepositoryException {
        return customers;
    }
}
