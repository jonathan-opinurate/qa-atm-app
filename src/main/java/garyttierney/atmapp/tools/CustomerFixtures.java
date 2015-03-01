package garyttierney.atmapp.tools;

import garyttierney.atmapp.model.Customer;

import java.util.HashSet;
import java.util.Set;

public class CustomerFixtures {
    public static final Set<Customer> FIXTURES = new HashSet<>();

    static {
        boolean[] superusers = new boolean[]{false, false, true};
        String[] accountNumbers = new String[]{"000101", "000202", "999999"};
        String[] pinNumbers = new String[]{"5412", "0219", "1234"};
        String[] forenames = new String[]{"George", "John", "Paul"};
        String[] surnames = new String[]{"Smith", "Paul", "John"};
        String[] addresses = new String[]{"Paisley", "London", "Jamaica"};
        double[] balances = new double[]{250.00, 350.00, 2500000.00};

        for (int i = 0; i < 3; i++) {
            Customer customer = new Customer();

            customer.setAccountNumber(accountNumbers[i]);
            customer.setPinNumber(pinNumbers[i]);
            customer.setForename(forenames[i]);
            customer.setSurname(surnames[i]);
            customer.setAddress(addresses[i]);
            customer.setBalance(balances[i]);
            customer.setSuperuser(superusers[i]);

            FIXTURES.add(customer);
        }
    }
}
