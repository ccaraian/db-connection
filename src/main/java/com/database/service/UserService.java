package com.database.service;

import java.sql.SQLException;

public interface UserService {

    void create(String firstName, String lastName) throws SQLException;
}
