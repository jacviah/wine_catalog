package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.dao.impl.DefaultUserDAO;
import by.jacviah.winery.model.User;
import org.junit.*;
import org.junit.Ignore;
import org.junit.rules.ExpectedException;

import java.time.Instant;


public class DefaultUserDAOTest {
    UserDAO dao = DefaultUserDAO.getInstance();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAddUser001() throws Exception {
        User user = new User("gamer " + Instant.now().toString(), "qwerty");
        User result = dao.addUser(user);
        Assert.assertTrue(result.getId()>0);
    }

    @Test
    public void testAddUser002() throws Exception {

        User user = new User("user", "user");
        thrown.expect(DaoException.class);
        dao.addUser(user);
    }

    @Test
    public void testFindUser001() throws Exception {

        User user = dao.findUser("user");
        Assert.assertNotNull(user.getUuid());
    }

    @Test
    public void testDeleteUser001() throws Exception {
        dao.addUser(new User("user_for_delete", "1"));
        Assert.assertNotNull(dao.removeUser("user_for_delete")==true);
    }

    @Test
    public void testDeleteUser002() throws Exception {

        Assert.assertTrue(dao.removeUser("user_for_delete")==false);
    }
}
