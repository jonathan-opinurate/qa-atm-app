package garyttierney.atmapp.tools;

import garyttierney.atmapp.model.Customer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class CreateSQLiteDatabaseTool {
    private final Connection connection;

    private static final Set<Customer> fixtureCustomers = new HashSet<>();
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("SQLite JDBC driver not available", e);
        }

        boolean[] superusers = new boolean[] {false, false, true};
        String[] accountNumbers = new String[] {"000101", "000202", "999999"};
        String[] pinNumbers = new String[] {"5412", "0219", "1234"};
        String[] forenames = new String[] {"George", "John", "Paul"};
        String[] surnames = new String[] {"Smith", "Paul", "John"};
        String[] addresses = new String[] {"Paisley", "London", "Jamaica"};
        double[] balances = new double[] {250.00, 350.00, 2500000.00};

        for(int i = 0; i < 3; i++) {
            Customer customer = new Customer();

            customer.setAccountNumber(accountNumbers[i]);
            customer.setPinNumber(pinNumbers[i]);
            customer.setForename(forenames[i]);
            customer.setSurname(surnames[i]);
            customer.setAddress(addresses[i]);
            customer.setBalance(balances[i]);
            customer.setSuperuser(superusers[i]);

            fixtureCustomers.add(customer);
        }
    }

    public CreateSQLiteDatabaseTool(Connection connection) {
        this.connection = connection;
    }

    public void run() {
        try {
            Statement stmt = connection.createStatement();

            // being very generous with column types, as sqlite doesn't support many specifics
            stmt.execute("CREATE TABLE customer\n" +
                    "(\n" +
                    "    account_number TEXT NOT NULL,\n" +
                    "    pin_number TEXT NOT NULL,\n" +
                    "    balance REAL NOT NULL,\n" +
                    "    superuser INTEGER NOT NULL,\n" +
                    "    withdrawal_limit INTEGER NOT NULL,\n" +
                    "    forename TEXT NOT NULL,\n" +
                    "    surname TEXT NOT NULL,\n" +
                    "    address TEXT NOT NULL,\n" +
                    "    blacklisted INTEGER NOT NULL,\n" +
                    "    PRIMARY KEY (account_number, pin_number)" +
                    ");\n");

            connection.setAutoCommit(false);

            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO customer " +
                    "   (account_number, pin_number, balance, superuser, withdrawal_limit, forename, surname, address, blacklisted)" +
                    "VALUES" +
                    "   (?, ?, ?, ?, ?, ?, ?, ?, 0);"
            );

            for (Customer customer : fixtureCustomers) {
                insertStatement.setString(1, customer.getAccountNumber());
                insertStatement.setString(2, customer.getPinNumber());
                insertStatement.setDouble(3, customer.getBalance());
                insertStatement.setBoolean(4, customer.isSuperuser());
                insertStatement.setInt(5, 250);
                insertStatement.setString(6, customer.getForename());
                insertStatement.setString(7, customer.getSurname());
                insertStatement.setString(8, customer.getAddress());

                insertStatement.executeUpdate();
            }

            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException("Couldn't rollback from db error! ", ex);
            }
        }
    }

    public static void main(String[] argv) {
        File propertiesFile = new File(argv[0]);

        Properties properties = new Properties();

        try (InputStream is = new FileInputStream(propertiesFile)){
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }

        try {
            Connection conn = DriverManager.getConnection(properties.getProperty("dsn"));
            new CreateSQLiteDatabaseTool(conn).run();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
