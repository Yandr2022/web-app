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
