package com.database;

import com.database.dao.UserDAO;
import com.database.dao.impl.UserDAOImpl;
import com.database.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class Main
{
    private static UserDAO userDAO = new UserDAOImpl();

    public static void main( String[] args ) throws SQLException {


        userDAO.create(new User("Caraian", "Constantin"));

        List<User> users = userDAO.getAll();

        users.forEach(Main::printUser);

        User user = users.get(0);
        user.setFirstName("FirstName");
        user.setLastName("LastName");

        userDAO.update(user);

        User updatedUser = userDAO.getById(user.getId());
        printUser(updatedUser);

        userDAO.delete(updatedUser.getId());

        users = userDAO.getAll();

        if(users.isEmpty()){
            System.out.println("Where no records left.");
        }
    }

    private static void printUser(User user) {
        System.out.println(user.toString());
    }
}
