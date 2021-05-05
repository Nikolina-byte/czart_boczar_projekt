package pwr.edu.czart_boczar_projekt.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class DBUtil {

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



