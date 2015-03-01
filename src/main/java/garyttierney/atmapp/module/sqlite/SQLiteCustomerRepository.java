package garyttierney.atmapp.module.sqlite;

import garyttierney.atmapp.database.CustomerRepository;
import garyttierney.atmapp.database.CustomerRepositoryException;
import garyttierney.atmapp.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SQLiteCustomerRepository implements CustomerRepository {
    private final Connection connection;

    public SQLiteCustomerRepository(Connection connection) throws SQLException {
        this.connection = connection;

        init();
    }

    private PreparedStatement findStatement;
    private PreparedStatement listStatement;
    private PreparedStatement updateStatement;

    public void init() throws SQLException {
        this.findStatement = connection.prepareStatement(
            "SELECT c.* FROM customer c WHERE c.account_number = ? AND c.pin_number = ?"
        );
        this.updateStatement = connection.prepareStatement(
            "UPDATE customer SET balance = ?, withdrawal_limit = ? WHERE account_number = ? AND pin_number = ?"
        );

        this.listStatement = connection.prepareStatement("SELECT c.* FROM customer c");
    }

    @Override
    public void updateCustomer(Customer customer) throws CustomerRepositoryException {
        try {
            updateStatement.setDouble(1, customer.getBalance());
            updateStatement.setInt(2, customer.getWithdrawalLimit());
            updateStatement.setString(3, customer.getAccountNumber());
            updateStatement.setString(4, customer.getPinNumber());

            updateStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new CustomerRepositoryException("Unknown error occurred when updating customer details", ex);
        }
    }

    @Override
    public Optional<Customer> findCustomer(String accountNumber, String pinNumber) throws CustomerRepositoryException {
        try {
            findStatement.setString(1, accountNumber);
            findStatement.setString(2, pinNumber);

            ResultSet resultSet = findStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(fromResultSet(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new CustomerRepositoryException("Unknown error occurred when finding customer", ex);
        }
    }

    @Override
    public Set<Customer> listCustomers() throws CustomerRepositoryException {
        try {
            Set<Customer> customers = new HashSet<>();
            ResultSet resultSet = listStatement.executeQuery();
            while (resultSet.next()) {
                customers.add(fromResultSet(resultSet));
            }
            return customers;
        } catch (SQLException ex) {
            throw new CustomerRepositoryException("Unknown error occurred when listing customers", ex);
        }
    }

    private Customer fromResultSet(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setSuperuser(resultSet.getBoolean("superuser"));
        customer.setBalance(resultSet.getDouble("balance"));
        customer.setWithdrawlLimit(resultSet.getInt("withdrawal_limit"));
        customer.setForename(resultSet.getString("forename"));
        customer.setSurname(resultSet.getString("surname"));
        customer.setAddress(resultSet.getString("address"));
        customer.setAccountNumber(resultSet.getString("account_number"));
        customer.setPinNumber(resultSet.getString("pin_number"));

        return customer;
    }
}
