package com.database.service.impl;

import com.database.dao.UserDAO;
import com.database.dao.impl.UserDAOImpl;
import com.database.entity.User;
import com.database.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public void create(String firstName, String lastName) throws SQLException {
        userDAO.create(new User(firstName, lastName));
    }
}
