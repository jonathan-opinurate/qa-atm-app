package garyttierney.atmapp.tools;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateSQLiteDatabaseTool {
    private final Connection connection;

    public CreateSQLiteDatabaseTool(Connection connection) {
        this.connection = connection;
    }

    public void run() {
        try {
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();

        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException("Couldn't rollback from db error! ", e);
            }
        }
    }
}
