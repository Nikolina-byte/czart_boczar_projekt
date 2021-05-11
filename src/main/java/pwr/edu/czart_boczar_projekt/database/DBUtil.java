package pwr.edu.czart_boczar_projekt.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/***
 * Abstract database main class
 */
public abstract class DBUtil {

    /***
     * The method responsible for closing the connection to the database.
     * @param conn A connection (session) with a specific database
     * @param statement The object used for executing a static SQL statement and returning the results it produces.
     * @param resultSet A ResultSet object maintains a cursor pointing to its current row of data. Initially the cursor is positioned before the first row.
     */
    protected static void close(Connection conn, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null)
                resultSet.close();

            if (statement != null)
                statement.close();

            if (conn != null)
                conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



