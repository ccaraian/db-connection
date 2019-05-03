package com.database.dao.impl;

import com.database.connection.DBConnection;
import com.database.dao.UserDAO;
import com.database.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private Connection connection = null;

    private DBConnection dbConnection = new DBConnection();

    @Override
    public void create(User user) throws SQLException {
        openConnection();
        if(connection != null){
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement("insert into user (first_name, last_name) values (?, ?)");
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getLastName());

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if(statement != null){
                    statement.close();
                }
                closeConnection();
            }
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        openConnection();
        if (connection != null) {
            try (Statement statement = connection.createStatement()) {
               ResultSet resultSet = statement.executeQuery("select * from user");
               List<User> users = new ArrayList<>();
               while (resultSet.next()){
                   Integer id = resultSet.getInt("id");
                   String firstName = resultSet.getString("first_name");
                   String lastName = resultSet.getString(3);
                   users.add(new User(id, firstName, lastName));
               }
               return users;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }

        return Collections.emptyList();
    }

    @Override
    public User getById(Integer id) throws SQLException {
        openConnection();
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement("select * from user where id=?")) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                Integer dbId = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString(3);
                return new User(dbId, firstName, lastName);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }

        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {
        openConnection();
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement("delete from user where id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }
    }

    @Override
    public void update(User user) throws SQLException {
        openConnection();
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement("update user set first_name=?, last_name=? where id=?")) {
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getLastName());
                statement.setInt(3, user.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }
    }

    private void openConnection(){
        try {
            Class.forName(dbConnection.getDriver());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            connection = DriverManager
                    .getConnection(dbConnection.getUrl(), dbConnection.getUsername(), dbConnection.getPassword());

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
    }

    private void closeConnection() throws SQLException {
        connection.close();
    }

}
