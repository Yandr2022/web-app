package org.example.dao.impl;


import org.example.dao.AbstractDAO;
import org.example.model.Office;
import org.example.model.User;
import org.example.util.DB_Util;


import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class UserDao extends AbstractDAO<User> {
private final String COLUMNS= "u.id as 'UserId', u.`name` as 'UserName', u.`email`,u.`password`, u.`isActive`" +
        ", u.`createdTime`, u.`updatedTime`, o.id as 'OfficeId', o.name as 'OfficeName', o.location, o.phone, o.fax";
public static RoleDao roleDao= new RoleDao();

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
        String sql = "UPDATE users_db SET name = ?, password = ?,updatedTime = CURRENT_TIMESTAMP WHERE email = ?";
        try (Connection connection = DB_Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
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

    public boolean activateAccount(User user) {
        if (user.isActive()){
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
        String sql = "SELECT "+ COLUMNS +" FROM `users_db` AS u JOIN offices AS o WHERE u.officeId = o.id ";
        Set userList = new LinkedHashSet<User>();

        try (Connection connection = DB_Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rset = preparedStatement.executeQuery();
            while (rset.next()) {
                User user = new User();
                Office office = new Office();
                user.setId(rset.getInt("UserId"));
                user.setEmail(rset.getString("email"));
                user.setName(rset.getString("UserName"));
                user.setPassword(rset.getString("password"));
                user.setActive(rset.getString("isActive").equals("Y"));
                user.setCreatedTime(rset.getTimestamp("createdTime"));
                user.setUpdatedTime(rset.getTimestamp("updatedTime"));
                office.setId(rset.getInt("officeId"));
                office.setName(rset.getString("officeName"));
                office.setLocation(rset.getString("location"));
                office.setPhone(rset.getString("phone"));
                office.setFax(rset.getString("fax"));
                user.setOffice(office);
                user.setRoles(roleDao.getAllByUser(user));
                userList.add(user);
            }
           System.out.println(userList.size());
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public User getByEmail(String email) {
        Connection connection = DB_Util.getConnection();
        String sql = "SELECT " + COLUMNS + " FROM  `users_db` AS u JOIN offices AS o WHERE u.officeId = o.id and u.email = '" + email + "'";
        Statement statement = null;
        ResultSet rset = null;
        User user = null;
        try {
            statement = connection.createStatement();
            rset = statement.executeQuery(sql);
            if (rset.next()) {
                user = new User();
                Office office = new Office();
                user.setId(rset.getInt("UserId"));
                user.setEmail(rset.getString("email"));
                user.setName(rset.getString("UserName"));
                user.setPassword(rset.getString("password"));
                user.setActive(rset.getString("isActive").equals("Y"));
                user.setCreatedTime(rset.getTimestamp("createdTime"));
                user.setUpdatedTime(rset.getTimestamp("updatedTime"));
                office.setId(rset.getInt("officeId"));
                office.setName(rset.getString("officeName"));
                office.setLocation(rset.getString("location"));
                office.setPhone(rset.getString("phone"));
                office.setFax(rset.getString("fax"));
                user.setOffice(office);
                user.setRoles(roleDao.getAllByUser(user));

            } else {
                System.out.println("User not found "+ email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB_Util.release(connection, statement, null, rset);
        }

        return user;
    }

    public static void main(String[] args) {
        User  user =
        new UserDao().getByEmail("sharkievich@gmail.com");
            System.out.println(user);


    }
}
