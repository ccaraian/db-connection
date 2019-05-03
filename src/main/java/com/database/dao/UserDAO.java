package com.database.dao;

import com.database.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    void create(User user) throws SQLException;

    List<User> getAll() throws SQLException;

    User getById(Integer id) throws SQLException;

    void delete(Integer id) throws SQLException;

    void update(User user) throws SQLException;
}
