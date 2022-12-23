package org.DAO.impl;

import org.DAO.AbstractDAO;
import org.util.DB_Util;

import java.sql.*;
import java.util.Set;

public class UserDao extends AbstractDAO<User> {


    public boolean insert(User user) {
        String sql = "INSERT INTO user( name, email, password ) VALUES (?, ?, ?)";
        try (Connection connection = DB_Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(1, user.getEmail());
            statement.setString(1, user.getPassword());
            if (statement.executeUpdate() == 1) {
                System.out.println("user was inserted successfully");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return false;
    }

    public boolean update(User user) {
        return false;
    }

    public boolean delete(User user) {
        return false;
    }

    public User getById(User user) {
        return null;
    }

    public Set<User> getAll() {
        return null;
    }

    public User getByEmail(String email) {
        Connection connection = DB_Util.getConnection();
        String sql = "SELECT * FROM user WHERE email = '" + email + "'";
        Statement statement = null;
        ResultSet set = null;
        User user = null;
        try {
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            if (set.next()) {
                user = new User();
                user.setEmail(email);
                user.setId(set.getInt(1));
                user.setName(set.getString("name"));
                user.setPassword(set.getString("password"));
            } else {
                System.out.println("user not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB_Util.release(connection, statement, null, set);
        }

        return user;
    }
}
