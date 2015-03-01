package garyttierney.atmapp.service;

import com.google.inject.Inject;
import garyttierney.atmapp.database.CustomerRepository;
import garyttierney.atmapp.database.CustomerRepositoryException;
import garyttierney.atmapp.model.Customer;

public class CustomerWithdrawalService {
    private final CustomerRepository customerRepository;

    @Inject
    public CustomerWithdrawalService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void handleWithdrawal(Customer customer, int amount) throws CustomerWithdrawalException {
        try {
            if (customer.getBalance() < amount) {
                throw new CustomerWithdrawalException("You have insufficient funds available to complete this transaction.");
            }

            if (customer.getWithdrawalLimit() < amount) {
                throw new CustomerWithdrawalException("This amount exceeds your remaining daily withdrawal limit.");
            }

            customer.setWithdrawlLimit(customer.getWithdrawalLimit() - amount);
            customer.setBalance(customer.getBalance() - amount);

            customerRepository.updateCustomer(customer);
        } catch (CustomerRepositoryException ex) {
            customer.setWithdrawlLimit(customer.getWithdrawalLimit() + amount);
            customer.setBalance(customer.getBalance() + amount);

            throw new CustomerWithdrawalException("An unknown error occurred while processing your withdrawal! Please try again later");
        }
    }
}
