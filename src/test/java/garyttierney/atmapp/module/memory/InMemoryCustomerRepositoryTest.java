package garyttierney.atmapp.module.memory;

import garyttierney.atmapp.CustomerRepositoryTest;
import garyttierney.atmapp.tools.CustomerFixtures;
import org.junit.Before;
import org.junit.Test;

public class InMemoryCustomerRepositoryTest extends CustomerRepositoryTest {

    @Test
    public void testUpdateCustomer() throws Exception {
        super.testUpdateCustomer();
    }

    @Test
    public void testFindCustomer_InvalidCredentials() throws Exception {
        super.testFindCustomer_InvalidCredentials();
    }

    @Test
    public void testFindCustomer() throws Exception {
        super.testFindCustomer();
    }

    @Test
    public void testListCustomers() throws Exception {
        super.testListCustomers();
    }

    @Before
    public void setUp() throws Exception {
        repository = new InMemoryCustomerRepository(CustomerFixtures.FIXTURES);
    }
}