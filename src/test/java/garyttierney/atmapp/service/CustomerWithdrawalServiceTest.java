package garyttierney.atmapp.service;

import garyttierney.atmapp.database.CustomerRepository;
import garyttierney.atmapp.database.CustomerRepositoryException;
import garyttierney.atmapp.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class CustomerWithdrawalServiceTest {

    @Test
    public void testHandleWithdrawal_Rollback() throws Exception {
        CustomerRepository mock = Mockito.mock(CustomerRepository.class);

        CustomerWithdrawalService withdrawalService = new CustomerWithdrawalService(mock);
        Customer customer = new Customer();
        customer.setBalance(300.00);
        customer.setWithdrawalLimit(300);

        Mockito.doThrow(new CustomerRepositoryException("Exception stub")).doNothing().when(mock).updateCustomer(customer);

        try {
            withdrawalService.handleWithdrawal(customer, 300);
            Assert.assertFalse("Withdrawal didn't throw exception when CustomerRepository threw an exception", true);
        } catch (CustomerWithdrawalException ex) {
            Assert.assertEquals(300.00, customer.getBalance(), 0);
            Assert.assertEquals(300, customer.getWithdrawalLimit());
        }
    }

    @Test
    public void testHandleWithdrawal_Successful() throws Exception {
        CustomerRepository mock = Mockito.mock(CustomerRepository.class);

        CustomerWithdrawalService withdrawalService = new CustomerWithdrawalService(mock);
        Customer customer = new Customer();
        customer.setBalance(300.00);
        customer.setWithdrawalLimit(300);

        Mockito.doNothing().when(mock).updateCustomer(customer);

        withdrawalService.handleWithdrawal(customer, 300);

        Assert.assertEquals(0.00, customer.getBalance(), 0);
        Assert.assertEquals(0, customer.getWithdrawalLimit());
    }

    @Test(expected = CustomerWithdrawalException.class)
    public void testHandleWithdrawal_InsufficientFunds() throws Exception {
        CustomerRepository mock = Mockito.mock(CustomerRepository.class);

        CustomerWithdrawalService withdrawalService = new CustomerWithdrawalService(mock);
        Customer customer = new Customer();
        customer.setBalance(200.00);
        customer.setWithdrawalLimit(300);

        Mockito.doNothing().when(mock).updateCustomer(customer);

        withdrawalService.handleWithdrawal(customer, 300);
    }

    @Test(expected = CustomerWithdrawalException.class)
    public void testHandleWithdrawal_OverWithdrawalLimit() throws Exception {
        CustomerRepository mock = Mockito.mock(CustomerRepository.class);

        CustomerWithdrawalService withdrawalService = new CustomerWithdrawalService(mock);
        Customer customer = new Customer();
        customer.setBalance(300.00);
        customer.setWithdrawalLimit(200);

        Mockito.doNothing().when(mock).updateCustomer(customer);

        withdrawalService.handleWithdrawal(customer, 300);
    }
}