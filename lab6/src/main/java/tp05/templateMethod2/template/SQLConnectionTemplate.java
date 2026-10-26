package tp05.templateMethod2.template;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * One interaction with the SQL database.
 * <p> Fairly low-level class.
 * You will probably prefer its derivative, SQLTemplate.
 * Uses the "template method" pattern
 *
 * @author rosmord
 */
public abstract class SQLConnectionTemplate<T> {

    /**
     * Executes the query associated with this template.
     */
    public final  T execute() {
        try (
                // Automatic closing with try-with-resources
                Connection connection = ConnectionFactory.getInstance().getConnection();) {
            T resultat = null;
            connection.setAutoCommit(false);
            try {
                resultat = doQuery(connection);
            } catch (SQLException | RuntimeException e) {
                connection.rollback();
                throw e;
            }
            connection.commit();
            return resultat;
        } catch (SQLException e) {
            throw new SQLTemplateException(e);
        }     
    }


    /**
     * Series of JDBC commands to execute within a connection.
     *
     *
     * @param db the connection (don't worry about closing it)
     * @throws java.sql.SQLException
     */
    protected abstract T doQuery(Connection db) throws SQLException;

}
