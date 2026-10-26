package tp05.templateMethod2.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A factory for the connections to use.
 *
 * @author rosmord
 */
public class ConnectionFactory {

    private static final ConnectionFactory INSTANCE = new ConnectionFactory();

    public static ConnectionFactory getInstance() {
        return INSTANCE;
    }

    private ConnectionFactory() {
        try {
            Class.forName("org.h2.Driver");
        } catch (final ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_Constantes.DB_URL, "sa", "");
        } catch (final SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
