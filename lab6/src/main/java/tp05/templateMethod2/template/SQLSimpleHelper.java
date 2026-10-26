package tp05.templateMethod2.template;

import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A set of handy methods to execute SQL queries.
 */
public class SQLSimpleHelper {
    /**
     * Static helper class: no instantiation.
     */
    private SQLSimpleHelper() {
    }

    /**
     * Inserts an entry into the database, and retrieves its id (always a long).
     *
     * @param sql  : the insertion query, with '?' in place of the variable
     *             parts.
     * @param args the values of the '?'
     * @return the id of the new entry.
     * @throws SQLTemplateException in case of trouble.
     */
    public static long insert(String sql, Object... args) {
        List<List<Object>> argList = Collections.singletonList(Arrays.asList(args));
        Long res = new InsertTemplateAux(sql, argList).execute();
        return res;
    }

    /**
     * Executes a modification on one entry.
     *
     * @param sql  a modification query; '?' marks arguments.
     * @param args the argument values.
     */
    public static void update(String sql, Object... args) {
        List<List<Object>> argList = Collections.singletonList(Arrays.asList(args));
        updateBatch(sql, argList);
    }

    /**
     * Executes a modification on a series of entries
     *
     * @param sql     a modification query; '?' marks arguments.
     * @param argList a list of tuples, each tuple corresponding to a
     *                series of arguments.
     */
    public static void updateBatch(String sql, List<List<Object>> argList) {
        new SQLUpdateTemplateAux(sql, argList).execute();
    }

    /**
     * Executes a query that must return exactly one integer (or null).
     *
     * @param sql
     * @param args
     * @return an Integer or null, depending on whether the query returns or not.
     * @throws SQLTemplateException if the query returns more than one result.
     */
    public static Integer sqlIntQuery(String sql, Object... args) {
        List<Integer> res = new SQLIntegerQueryAux(sql).execute(args);
        if (res.isEmpty())
            return null;
        else if (res.size() == 1)
            return res.get(0);
        else
            throw new SQLTemplateException("query returned " + res.size() + " integers " + res);
    }

    public static Long sqlLongQuery(String sql, Object... args) {
        List<Long> res = new SQLLongQueryAux(sql).execute(args);
        if (res.isEmpty())
            return null;
        else if (res.size() == 1)
            return res.get(0);
        else
            throw new SQLTemplateException("query returned " + res.size() + " integers " + res);
    }

    /**
     * Template for insertion, with id retrieval.
     */
    private static class InsertTemplateAux extends SQLStatementTemplate<Long> {

        public InsertTemplateAux(String sql, List<List<Object>> argList) {
            super(sql, argList);
        }

        @Override
        protected Long initResult() {
            return null; // Will be set at the end!
        }

        @Override
        protected void performStatement(Long currentResult, PreparedStatement pst) throws SQLException {
            pst.executeUpdate();
        }

        @Override
        protected Long performAtEnd(Connection db, Long result) throws SQLException {
            String LAST_ID_REQUEST = "SELECT SCOPE_IDENTITY()";
            try (Statement st = db.createStatement(); ResultSet rs = st.executeQuery(LAST_ID_REQUEST)) {
                if (rs.next()) {
                    return rs.getLong(1);
                } else {
                    throw new SQLTemplateException("insert did not work?");
                }
            }
        }
    }

    private static class SQLUpdateTemplateAux extends SQLStatementTemplate<Void> {

        public SQLUpdateTemplateAux(String sql, List<List<Object>> argList) {
            super(sql, argList);
        }

        @Override
        protected Void initResult() {
            return null;
        }

        @Override
        protected void performStatement(Void currentResult, PreparedStatement pst) throws SQLException {
            pst.executeUpdate();
        }
    }

    private static class SQLIntegerQueryAux extends SQLQueryTemplate<Integer> {
        public SQLIntegerQueryAux(String sql) {
            super(sql);
        }

        @Override
        protected Integer extractData(ResultSet res) throws SQLException {
            return res.getInt(1);
        }
    }

    private static class SQLLongQueryAux extends SQLQueryTemplate<Long> {
        public SQLLongQueryAux(String sql) {
            super(sql);
        }

        @Override
        protected Long extractData(ResultSet res) throws SQLException {
            return res.getLong(1);
        }
    }
}