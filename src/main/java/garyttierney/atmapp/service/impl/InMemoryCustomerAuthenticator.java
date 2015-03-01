package garyttierney.atmapp.service.impl;

import garyttierney.atmapp.model.Customer;
import garyttierney.atmapp.service.CustomerAuthenticationException;
import garyttierney.atmapp.service.CustomerAuthenticationService;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class InMemoryCustomerAuthenticator implements CustomerAuthenticationService {
    public Set<Customer> customerSet;

    public InMemoryCustomerAuthenticator(Set<Customer> customers) {
        this.customerSet = Collections.unmodifiableSet(customers);
    }

    @Override
    public Customer authenticate(String accountNumber, final String pinNumber) throws CustomerAuthenticationException {
        Optional<Customer> customer = customerSet.stream()
                .filter((c) -> accountNumber.equals(c.getAccountNumber()) && pinNumber.equals(c.getPinNumber()))
                .findFirst();

        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new CustomerAuthenticationException(INVALID_CREDENTIALS_MSG);
        }
     }
}
