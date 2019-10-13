package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.dao.impl.DefaultUserDAO;
import by.jacviah.winery.model.User;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.ExpectedException;


public class DefaultUserDAOTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Ignore
    @org.junit.Test
    public void testAddUser001() throws Exception {
        UserDAO dao = DefaultUserDAO.getInstance();
        User user = new User("gamer", "qwerty");
        User result = dao.addUser(user);
        Assert.assertTrue(result.getId()>0);
    }
    @Ignore
    @org.junit.Test
    public void testAddUser002() throws Exception {

        UserDAO dao = DefaultUserDAO.getInstance();
        User user = new User("gamer", "qwerty");
        thrown.expect(DaoException.class);
        dao.addUser(user);
    }
    @Ignore
    @org.junit.Test
    public void testFindUser001() throws Exception {
        UserDAO dao = DefaultUserDAO.getInstance();
        User user = dao.findUser("gamer");
        Assert.assertNotNull(user.getUuid());
    }
    @Ignore
    @org.junit.Test
    public void testDeleteUser001() throws Exception {
        UserDAO dao = DefaultUserDAO.getInstance();
        Assert.assertNotNull(dao.removeUser("gamer")==true);
    }
    @Ignore
    @org.junit.Test
    public void testDeleteUser002() throws Exception {
        UserDAO dao = DefaultUserDAO.getInstance();
        Assert.assertTrue(dao.removeUser("gamer")==false);
    }
}
