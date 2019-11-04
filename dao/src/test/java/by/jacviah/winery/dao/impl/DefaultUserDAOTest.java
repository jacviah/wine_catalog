package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Role;
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
    public void testFindUser001() throws Exception {

        User user = dao.findUser("user");
        Assert.assertNotNull(user.getUuid());
    }

    @Test
    public void testAddUser001() throws Exception {
        User user = User.UserBuilder.anUser()
                .withUsername("gamer " + Instant.now().toString())
                .withPassword("1")
                .withRole(Role.USER)
                .build();
        dao.addUser(user);
        user = dao.findUser(user.getUsername());
        Assert.assertTrue(user.getRole().equals(Role.USER));
        Assert.assertTrue(user.getPassword().equals("1"));
        dao.removeUser(user);
    }

    @Test
    public void testAddUser002() throws Exception {
        User user = new User("user", "user");
        thrown.expect(DaoException.class);
        dao.addUser(user);
    }

    @Test
    public void testDeleteUser001() throws Exception {
        User user = User.UserBuilder.anUser()
                .withUsername("user_for_delete")
                .withPassword("1")
                .withRole(Role.USER)
                .build();
        dao.addUser(user);
        User fromDB = dao.findUser(user.getUsername());
        dao.removeUser(fromDB);
        Assert.assertNull(fromDB = dao.findUser(user.getUsername()));
    }

  /*    @Test
    public void testDeleteUser002() throws Exception {

        Assert.assertTrue(dao.removeUser("user_for_delete")==false);
    }*/

}
