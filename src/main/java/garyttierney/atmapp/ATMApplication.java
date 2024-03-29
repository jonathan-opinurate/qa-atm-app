package garyttierney.atmapp;

import com.google.inject.Guice;
import com.google.inject.Injector;
import garyttierney.atmapp.database.CustomerRepository;
import garyttierney.atmapp.database.CustomerRepositoryException;
import garyttierney.atmapp.model.Customer;
import garyttierney.atmapp.module.memory.InMemoryDatabaseModule;
import garyttierney.atmapp.module.sqlite.SQLiteDatabaseModule;
import garyttierney.atmapp.swing.view.CustomerValidationView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Entry point for the ATMApplication, instantiates the required modules and boots up the validation view.
 */
public class ATMApplication {
    public static void main(String[] argv) {
        if (argv.length < 2) {
            System.err.println("Usage: java garyttierney.atmapp.ATMApplication [options] <configuration-file>");
            System.err.println("\t Options:");
            System.err.println("\t\t --sqlite - Run the ATMApplication with the sqlite database module.");
            System.err.println("\t\t --memory [default] - Run the ATMApplication with the in memory database module.");
        }

        String databaseFlag = argv.length < 2 ? "--memory" : argv[0];
        String propertiesFilePath = argv.length < 2 ? argv[0] : argv[1];
        File propertiesFile = new File(propertiesFilePath);

        Properties properties = new Properties();

        try (InputStream is = new FileInputStream(propertiesFile)){
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }

        Injector injector = null;
        if ("--sqlite".equals(databaseFlag)) {
            injector = Guice.createInjector(new SQLiteDatabaseModule(properties));
        } else if ("--memory".equals(databaseFlag)) { // default
            injector = Guice.createInjector(new InMemoryDatabaseModule());
        } else {
            throw new RuntimeException("Got unknown value \"" + databaseFlag + "\" for database flag.");
        }

        ATMApplicationContext ctx = new ATMApplicationContext(injector);
        CustomerRepository customerRepository = ctx.getService(CustomerRepository.class);
        try {
            for(Customer customer : customerRepository.listCustomers()) {
                customer.setWithdrawalLimit(250);
                customerRepository.updateCustomer(customer);
            }
        } catch (CustomerRepositoryException e) {
            throw new RuntimeException("Encountered error when trying to reset daily withdrawal limits!", e);
        }

        ctx.switchTo(new CustomerValidationView(ctx));
    }
}
