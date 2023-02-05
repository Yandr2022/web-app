package org.example.dao.impl;

import org.example.dao.AbstractDAO;
import org.example.model.Role;
import org.example.model.User;
import org.example.util.DB_Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class RoleDao extends AbstractDAO<Role> {
    private final String COLUMNS="ur.UserId, r.Id as RolesId , r.name, r.description ";



    @Override
    public boolean insert(Role role) {
        return false;
    }

    @Override
    public boolean update(Role role) {
        return false;
    }

    @Override
    public boolean delete(Role role) {
        return false;
    }

    @Override
    public Role getById(Role role) {
        return null;
    }
    public Role getByName(String name) {
        String sql = "SELECT id, name, description FROM roles WHERE name = ?";
        Role role = null;
        try (Connection connection = DB_Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                role.setDescription(rs.getString("description"));

            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return role;
    }

    @Override
    public Set<Role> getAll() {
        return null;
    }
    public Set<Role> getAllByUser(User user) {
        String sql = "SELECT "+ COLUMNS +" FROM `user_roles` as ur join roles as r  ON r.Id = ur.RolesId and ur.UserId = ?";
        Set rolesList = null;

        try (Connection connection = DB_Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,user.getId());
            ResultSet rset = preparedStatement.executeQuery();
            rolesList = new HashSet<Role>();
            while (rset.next()) {
                Role role = new Role();
                role.setId(rset.getInt("RolesId"));
                role.setName(rset.getString("name"));
                role.setDescription(rset.getString("description"));
                rolesList.add(role);
            }
            } catch (SQLException e) {
           e.printStackTrace();
        }

        return rolesList;
    }
}
