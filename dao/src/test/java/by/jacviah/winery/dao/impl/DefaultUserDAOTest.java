package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.model.User;
import org.junit.Assert;
import org.junit.Ignore;

import java.util.UUID;

/**
 * Created by jacviah on 08.10.2019.
 */
public class DefaultUserDAOTest {
    @Ignore
    @org.junit.Test
    public void testAddUser001() throws Exception {

        UserDAO dao = DefaultUserDAO.getInstance();
        User user = new User("gamer", "qwerty");
        dao.addUser(user);
        Assert.assertTrue(1==1);
    }
    @Ignore
    @org.junit.Test
    public void testAddUser002() throws Exception {
        UserDAO dao = DefaultUserDAO.getInstance();
        User user = new User("gamer2", "qwerty");
        dao.addUser(user);
        Assert.assertNotNull(1==1);
    }
}
