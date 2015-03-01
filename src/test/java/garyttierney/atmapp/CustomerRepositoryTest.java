package garyttierney.atmapp;

import garyttierney.atmapp.database.CustomerRepository;
import garyttierney.atmapp.model.Customer;
import garyttierney.atmapp.tools.CustomerFixtures;
import org.junit.Assert;

import java.util.Optional;
import java.util.Set;

/**
 * @see garyttierney.atmapp.tools.CustomerFixtures
 */
public abstract class CustomerRepositoryTest  {

    protected CustomerRepository repository;

    public abstract void setUp() throws Exception;

    public void testFindCustomer() throws Exception {
        Optional<Customer> customer = repository.findCustomer("999999", "1234");

        Assert.assertTrue(customer.isPresent());
        Assert.assertEquals("999999", customer.get().getAccountNumber());
        Assert.assertEquals("1234", customer.get().getPinNumber());
    }

    public void testFindCustomer_InvalidCredentials() throws Exception {
        Optional<Customer> customer = repository.findCustomer("12391", "2321");

        Assert.assertFalse(customer.isPresent());
    }

    public void testUpdateCustomer() throws Exception {
        Customer customer = repository.findCustomer("999999", "1234").get();
        customer.setBalance(550.00);
        repository.updateCustomer(customer);

        Customer newCustomer = repository.findCustomer("999999", "1234").get();

        Assert.assertEquals(550.00, newCustomer.getBalance(), 0);
    }

    public void testListCustomers() throws Exception {
        Set<Customer> customers = repository.listCustomers();

        Assert.assertEquals(CustomerFixtures.FIXTURES.size(), customers.size());
    }
}
