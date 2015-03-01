package garyttierney.atmapp.module.sqlite;

import com.google.inject.Binder;
import com.google.inject.Module;
import garyttierney.atmapp.database.CustomerRepository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLiteDatabaseModule implements Module {
    private final Properties properties;
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("SQLite JDBC driver not available", e);
        }
    }

    public SQLiteDatabaseModule(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void configure(Binder binder) {
        String dsn = properties.getProperty("dsn");

        try {
            binder.bind(CustomerRepository.class).toInstance(new SQLiteCustomerRepository(DriverManager.getConnection(dsn)));
        } catch (SQLException e) {
            throw new RuntimeException("Unable to initialize SQLite database module", e);
        }
    }
}
