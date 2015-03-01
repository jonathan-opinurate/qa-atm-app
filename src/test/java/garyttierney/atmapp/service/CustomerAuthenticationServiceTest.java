package garyttierney.atmapp.service;

import garyttierney.atmapp.database.CustomerRepository;
import garyttierney.atmapp.database.CustomerRepositoryException;
import garyttierney.atmapp.model.Customer;
import org.junit.Assert;
import org.mockito.Mockito;

import java.util.Optional;

public class CustomerAuthenticationServiceTest {

    @org.junit.Test
    public void testAuthenticate() throws Exception {
        CustomerRepository mock = Mockito.mock(CustomerRepository.class);

        CustomerAuthenticationService authenticationService = new CustomerAuthenticationService(mock);
        Customer customer = new Customer();
        customer.setBalance(300.00);
        customer.setWithdrawalLimit(300);
        customer.setAccountNumber("00010001");
        customer.setPinNumber("1234");

        Mockito.doReturn(Optional.of(customer)).when(mock).findCustomer("00010001", "1234");

        Customer newCustomer = authenticationService.authenticate("00010001", "1234");

        Assert.assertEquals(customer.getBalance(), newCustomer.getBalance(), 0);
        Assert.assertEquals(customer.getWithdrawalLimit(), newCustomer.getWithdrawalLimit());
        Assert.assertEquals(customer.getAccountNumber(), newCustomer.getAccountNumber());
        Assert.assertEquals(customer.getPinNumber(), newCustomer.getPinNumber());
    }

    @org.junit.Test(expected = CustomerAuthenticationException.class)
    public void testAuthenticate_CouldntFetchUser() throws Exception {
        CustomerRepository mock = Mockito.mock(CustomerRepository.class);

        CustomerAuthenticationService authenticationService = new CustomerAuthenticationService(mock);
        Mockito.doThrow(new CustomerRepositoryException("empty")).when(mock).findCustomer("00010001", "1234");

        authenticationService.authenticate("00010001", "1234");
    }

    @org.junit.Test(expected = CustomerAuthenticationException.class)
    public void testAuthenticate_InvalidCredentials() throws Exception {
        CustomerRepository mock = Mockito.mock(CustomerRepository.class);

        CustomerAuthenticationService authenticationService = new CustomerAuthenticationService(mock);
        Mockito.doReturn(Optional.<Customer>empty()).when(mock).findCustomer("00010001", "1234");

        authenticationService.authenticate("00010001", "1234");
    }
}