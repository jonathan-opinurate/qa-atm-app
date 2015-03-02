package garyttierney.atmapp.module.sqlite;

import garyttierney.atmapp.CustomerRepositoryTest;
import garyttierney.atmapp.tools.CreateSQLiteDatabaseTool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteCustomerRepositoryTest extends CustomerRepositoryTest {

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
        File file = new File("tmp_db.sqlite");
        if (file.exists()) {
            file.delete();
        }

        Connection conn = DriverManager.getConnection("jdbc:sqlite:tmp_db.sqlite");
        CreateSQLiteDatabaseTool createSQLiteDatabaseTool = new CreateSQLiteDatabaseTool(conn);

        createSQLiteDatabaseTool.run();
        repository = new SQLiteCustomerRepository(conn);
    }

    @After
    public void tearDown() throws Exception {
        File file = new File("tmp_db.sqlite");
        file.delete();
    }

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("SQLite JDBC driver not available", e);
        }
    }
}