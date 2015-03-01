package garyttierney.atmapp.database;

import garyttierney.atmapp.model.Customer;

import java.util.Optional;
import java.util.Set;

public interface CustomerRepository {
    /**
     * Update a customers withdrawalLimit and balance in the database.
     *
     * @param customer The customer to update.
     * @throws CustomerRepositoryException If an error occurred while executing the update.
     */
    void updateCustomer(Customer customer) throws CustomerRepositoryException;

    /**
     * Lookup a customer by their accountNumber and pinNumber and return an Optional object with the result of the query.
     *
     * @param accountNumber The account number (username) of the customer.
     * @param pinNumber The pin number (password) of the customer.
     * @return An {@link Optional} object either empty, if no customer was found, or with the found customer attached.
     * @throws CustomerRepositoryException If an error occurred while performing the lookup.
     */
    Optional<Customer> findCustomer(String accountNumber, String pinNumber) throws CustomerRepositoryException;

    /**
     * Lookup all customers in the database.
     *
     * @return All of the customers available in the database.
     * @throws CustomerRepositoryException If an error occurred while performing the lookup.
     */
    Set<Customer> listCustomers() throws CustomerRepositoryException;
}
