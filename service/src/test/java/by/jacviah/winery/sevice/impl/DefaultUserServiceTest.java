package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.impl.DefaultUserDAO;
import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.exception.DaoException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
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

        User user = new User("admin2", "pass");
        when(dao.findUser("admin2")).thenReturn(user);
        User userFromDb = service.findUser("admin2");
        assertNotNull(userFromDb);
        assertEquals(userFromDb.getUsername(), "admin2");
        assertNotNull(userFromDb.getPassword(), "pass");
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User("admin2", "pass");
        when(dao.addUser(user)).thenReturn(user);
        User userFromDb = service.createUser("admin2", "pass");
        assertEquals(userFromDb.getUsername(), "admin2");
        assertEquals(userFromDb.getPassword(), "pass");
        assertEquals(userFromDb.getRole(), Role.USER);
    }


}