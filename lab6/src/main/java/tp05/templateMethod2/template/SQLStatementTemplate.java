package tp05.templateMethod2.template;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Base class for implementing Queries and Updates.
 * Uses the "template method" pattern.
 *
 */
public abstract class SQLStatementTemplate<T> extends SQLConnectionTemplate<T> {

    private final String sql;

    private final List<List<Object>> argList;

    protected SQLStatementTemplate(final String sql, final List<List<Object>> argList) {
        this.sql = sql;
        this.argList = argList;
    }

    /**
     * Creates an object in which the result will be accumulated.
     * This object will be returned at the end of the query.
     * @return
     */
    protected abstract T initResult();

    @Override
    protected final T doQuery(final Connection db) throws SQLException {
        T result = initResult();
        try (PreparedStatement pst = db.prepareStatement(sql)) {
            for (final List<Object> args : argList) {
                // For each tuple of arguments...
                for (int i = 0; i < args.size(); i++) {
                    pst.setObject(i + 1, args.get(i));
                }
                performStatement(result, pst);
            }
            result = performAtEnd(db, result);
            return result;
        }
    }

    /**
     * Method (optional) called at the end of the template.
     * <p>Allows recomputing the result afterwards, e.g.
     * by executing a new query.
     *
     * <p> This method was introduced essentially to be able
     * to execute a select last_insert_id() (or equivalent) after an insert.
     *
     * <p> By default, leaves the result untouched.
     * @param db
     * @param result
     * @return the result, possibly modified.
     */
    protected T performAtEnd(Connection db, T result) throws SQLException {
        return result;
    }

    /**
     * Executes the preparedStatement for the SQL query passed as a constructor
     * parameter, initialized with one of the argument tuples.
     *
     * @param currentResult the object in which to accumulate possible
     *                      results.
     * @param pst           the preparedStatement to execute.
     * @throws SQLException
     */
    protected abstract void performStatement(T currentResult, PreparedStatement pst) throws SQLException;

}