package org.example.dao.impl;

import org.example.dao.AbstractDAO;
import org.example.model.User;
import org.example.util.DB_Util;


import java.sql.*;
import java.util.Set;

public class UserDao extends AbstractDAO<User> {


    public boolean insert(User user) {
        String sql = "INSERT INTO users_db ( name, email, password ) VALUES (?, ?, ?)";
        try (Connection connection = DB_Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
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

    @Override
    public boolean update(User user) {
        return false;
    }

    public boolean activateAccount(User user) {
        if (user.getIsActivate().equals("Y")){
            return false;
            }
            String sql = " UPDATE users_db SET isActive ='Y',`updatedTime`= CURRENT_TIMESTAMP WHERE email = ?";
        try (Connection connection = DB_Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getEmail());
            if (statement.executeUpdate() == 1) {
                System.out.println("user was updated successfully");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
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
        String sql = "SELECT * FROM users_db WHERE email = '" + email + "'";
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
                user.setIsActivate(set.getString("isActive"));
            } else {
                System.out.println("User not found "+ email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB_Util.release(connection, statement, null, set);
        }

        return user;
    }
}
