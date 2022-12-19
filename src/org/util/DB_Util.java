package org.util;

import java.sql.*;

import static org.util.AppConstance.*;

public class DB_Util {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.printf("Connection " + connection);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void release(Connection connection, Statement statement, PreparedStatement prdstatement, ResultSet set) {
        try {
            if (connection != null) {
                System.out.printf("Connection is closed " + connection);
                connection.close();
            }
            if (statement != null) {
                System.out.printf("statement is closed " + statement);
                ;
                statement.close();
            }
            if (prdstatement != null) {
                System.out.printf("prdstatement is closed " + prdstatement);
                prdstatement.close();
            }
            if (set != null) {
                System.out.printf("set is closed " + set);
                set.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        Connection connection = getConnection();
    }
}
