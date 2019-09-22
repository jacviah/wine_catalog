package by.jacviah.winery.dao.impl;

import static org.junit.Assert.*;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.model.User;
import org.junit.Assert;

public class MapUserDAOTest {

    @org.junit.Test
    public void testAddUser001() throws Exception {

        UserDAO dao = new MapUserDAO();
        User user = new User("gamer", "qwerty");
        dao.addUser(user);
        Assert.assertTrue(dao.getAll().contains(user));
    }

    @org.junit.Test
    public void testAddUser002() throws Exception {
        UserDAO dao = new MapUserDAO();
        User user = new User("gamer2", "qwerty");
        dao.addUser(user);
        Assert.assertNotNull(dao.getUUID(user));
    }

}