package tp05.templateMethod2.template;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * SQLQueryTemplate. Uses the "template method" pattern to execute a
 * SQL query within a connection, properly handling connection
 * opening and closing, and returning the result.
 * <p>
 * Note: this class systematically opens one connection per
 * query, so it is fairly inefficient.
 */
public abstract class SQLQueryTemplate<T> {
    private final String sql;

    protected SQLQueryTemplate(String sql) {
        this.sql = sql;
    }

    /**
     * Executes a single query.
     *
     * @param <T>
     * @param sql  : the SQL code of the query (? for each variable argument)
     * @param args : the variable arguments to pass.
     * @return the result of the query.
     */
    public List<T> execute(final Object... args) {
        List<List<Object>> argList = Collections.singletonList(Arrays.asList(args));
        return execute(argList);
    }

    /**
     * Executes a series of queries, one for each tuple in a list
     * of arguments.
     *
     * @param sql     the query to execute
     * @param argList the values to pass to this query.
     * @return
     */
    public List<T> execute(List<List<Object>> argList) {
        List<T> resultat = new SQLQueryTemplateAux(sql, argList).execute();
        return resultat;
    }

    protected abstract T extractData(ResultSet res) throws SQLException;

    private class SQLQueryTemplateAux extends SQLStatementTemplate<List<T>> {

        public SQLQueryTemplateAux(String sql, List<List<Object>> argList) {
            super(sql, argList);
        }

        @Override
        protected List<T> initResult() {
            return new ArrayList<>();
        }

        @Override
        protected void performStatement(List<T> currentResult, PreparedStatement pst) throws SQLException {
            try (ResultSet resultSet = pst.executeQuery()) {
                while (resultSet.next()) {
                    currentResult.add(extractData(resultSet));
                }
            }
        }
    }
}