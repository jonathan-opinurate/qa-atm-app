package garyttierney.atmapp.service.impl;

import garyttierney.atmapp.model.Customer;
import garyttierney.atmapp.service.CustomerAuthenticationException;
import garyttierney.atmapp.service.CustomerAuthenticationService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteCustomerAuthenticator implements CustomerAuthenticationService {
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("SQLite JDBC driver not available", e);
        }
    }

    private final Connection jdbcConnection;
    private final PreparedStatement authStatement;


    public SQLiteCustomerAuthenticator(Connection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
        try {
            this.authStatement = jdbcConnection.prepareStatement(
                    "SELECT c.* FROM customer c WHERE c.account_number = ? AND c.pin_number = ?"
            );
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create authentication statement", e);
        }
    }

    @Override
    public Customer authenticate(String accountNumber, String pinNumber) throws CustomerAuthenticationException {
        try {
            authStatement.setString(1, accountNumber);
            authStatement.setString(2, pinNumber);

            ResultSet resultSet = authStatement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setSuperuser(resultSet.getBoolean("superuser"));
                customer.setBalance(resultSet.getDouble("balance"));
                customer.setWithdrawlLimit(resultSet.getInt("withdrawl_limit"));
                customer.setForename(resultSet.getString("forname"));
                customer.setSurname(resultSet.getString("surname"));
                customer.setAddress(resultSet.getString("address"));
                customer.setAccountNumber(accountNumber);
                customer.setPinNumber(pinNumber);

                return customer;
            } else {
                throw new CustomerAuthenticationException(INVALID_CREDENTIALS_MSG);
            }
        } catch (SQLException e) {
            throw new CustomerAuthenticationException("Unknown error occurred while contacting the database", e);
        }
    }
}
