package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.exception.DaoException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultUserServiceTest {

    @Mock
    UserDAO dao;

    @InjectMocks
    DefaultUserService service;

    @Test
    public void testUserNotExist() throws DaoException {
       when(dao.findUser("admin")).thenReturn(null);
       User user = service.findUser("admin");
       assertNull(user);
    }

    @Test
    public void testUserIsExist() throws DaoException {
        when(dao.findUser("admin")).thenReturn(new User("admin", "pass"));
        User userFromDb = service.findUser("admin");
        assertNotNull(userFromDb);
        assertEquals(userFromDb.getUsername(), "admin");
        assertNotNull(userFromDb.getPassword(), "pass");
    }

/*    @Test
    public void testFindUser() throws Exception {

        User user = new User("gamer", "qwerty");
        Assert.assertEquals(service.findUser("gamer"), user);
    }
    @Ignore
    @Test
    public void testCreateUser() throws Exception {
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();
        Assert.assertNull(service.createUser("player", "123"));
    }*/

    @Ignore
    @Test
    public void testLogin() throws Exception {

    }

    @Ignore
    @Test
    public void testInit() throws Exception {

    }
}