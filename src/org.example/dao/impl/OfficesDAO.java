package org.example.dao.impl;

import org.example.dao.AbstractDAO;
import org.example.model.Office;
import org.example.model.User;
import org.example.util.DB_Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class OfficesDAO extends AbstractDAO<Office> {
    @Override
    public boolean insert(Office office) {
        return false;
    }

    @Override
    public boolean update(Office office) {
        return false;
    }

    @Override
    public boolean delete(Office office) {
        return false;
    }

    @Override
    public Office getById(Office office) {
        return null;
    }

    public Office getByLocation(String location) {
      String sql = "SELECT * FROM offices WHERE location = ?";
      Office office =null ;

      try (Connection connection = DB_Util.getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
preparedStatement.setString(1, location);
          ResultSet resultSet = preparedStatement.executeQuery();

          if (resultSet.next()) {
               office = new Office();
              office.setId(Integer.parseInt(resultSet.getString("id")));
              office.setName(resultSet.getString("name"));
              office.setLocation(location);
              office.setPhone(resultSet.getString("phone"));
              office.setFax(resultSet.getString("fax"));

          }
      } catch (SQLException e) {
          e.printStackTrace();
          throw new RuntimeException(e);
      }
      return office;
    }

    @Override
    public Set<Office> getAll() {
        String sql = "SELECT * FROM offices ORDER BY id";
        Set<Office> officesList = new LinkedHashSet<Office>();

        try (Connection connection = DB_Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Office office = new Office();
                office.setId(Integer.parseInt(resultSet.getString("id")));
                office.setName(resultSet.getString("name"));
                office.setLocation(resultSet.getString("location"));
                office.setPhone(resultSet.getString("phone"));
                office.setFax(resultSet.getString("fax"));
                officesList.add(office);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return officesList;
    }
}
