package garyttierney.atmapp.module.memory;

import com.google.inject.Binder;
import com.google.inject.Module;

import garyttierney.atmapp.database.CustomerRepository;
import garyttierney.atmapp.tools.CustomerFixtures;

public class InMemoryDatabaseModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(CustomerRepository.class).toInstance(new InMemoryCustomerRepository(CustomerFixtures.FIXTURES));
    }
}
