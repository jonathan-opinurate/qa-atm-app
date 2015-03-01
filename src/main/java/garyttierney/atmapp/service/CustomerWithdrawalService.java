package garyttierney.atmapp.service;

import com.google.inject.Inject;
import garyttierney.atmapp.database.CustomerRepository;
import garyttierney.atmapp.database.CustomerRepositoryException;
import garyttierney.atmapp.model.Customer;

/**
 * This class is responsible for managing customer withdrawals.
 */
public class CustomerWithdrawalService {
    private final CustomerRepository customerRepository;

    /**
     * Create a new CustomerWithdrawalService instance with the specified <code>customerRepository</code>.
     * @param customerRepository A reference to the customer repository to use for handling withdrawals.
     */
    @Inject
    public CustomerWithdrawalService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Handles a withdrawal request from a customer and throws an exception if it could not be processed.
     *
     * @param customer The customer wishing to withdraw cash.
     * @param amount The amount the customer wishes to withdraw.
     * @throws CustomerWithdrawalException Thrown if the customer has insufficient funds, has exceeded their daily withdrawal limit
     *                                     or if there was an error updating the customer database when processing the withdrawal.
     */
    public void handleWithdrawal(Customer customer, int amount) throws CustomerWithdrawalException {
        try {
            if (customer.getBalance() < amount) {
                throw new CustomerWithdrawalException("You have insufficient funds available to complete this transaction.");
            }

            if (customer.getWithdrawalLimit() < amount) {
                throw new CustomerWithdrawalException("This amount exceeds your remaining daily withdrawal limit.");
            }

            customer.setWithdrawalLimit(customer.getWithdrawalLimit() - amount);
            customer.setBalance(customer.getBalance() - amount);

            customerRepository.updateCustomer(customer);
        } catch (CustomerRepositoryException ex) {
            //rollback to their original balance if we couldn't update it
            customer.setWithdrawalLimit(customer.getWithdrawalLimit() + amount);
            customer.setBalance(customer.getBalance() + amount);

            throw new CustomerWithdrawalException("An unknown error occurred while processing your withdrawal! Please try again later", ex);
        }
    }
}
