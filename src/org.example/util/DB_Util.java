package org.example.util;

import java.sql.*;

import static org.example.util.AppConstants.*;


public class DB_Util {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException |ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
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
                System.out.printf("Statement is closed " + statement);
                statement.close();
            }
            if (prdstatement != null) {
                System.out.printf("PrdStatement is closed " + prdstatement);
                prdstatement.close();
            }
            if (set != null) {
                System.out.printf("Set is closed " + set);
                set.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
//    public static void main(String[] args) {
//        System.out.println(new Date().toString());
//        Connection connection = DB_Util.getConnection();
//        String sql = "SELECT * FROM `users_db` WHERE email = 'sharkievich@mail.ru';";
//        Statement statement = null;
//        ResultSet set = null;
//
//        try {
//            statement = connection.createStatement();
//            set = statement.executeQuery(sql);
//            System.out.println(set.getInt(1));
//
//
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
