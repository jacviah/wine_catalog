package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.config.DaoConfig;
import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.exception.DaoException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@RunWith(JUnitPlatform.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
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
        User init = User.UserBuilder.anUser()
                .withUsername("admin2")
                .withPassword("pass")
                .build();
        User founded = User.UserBuilder.anUser()
                .withId(1L)
                .withUsername("admin2")
                .withPassword("pass")
                .withRole(Role.USER)
                .build();
        when(dao.addUser(init)).thenReturn(true);
        when(dao.findUser(init.getUsername())).thenReturn(null);
        service.createUser("admin2", "pass");
        when(dao.findUser(init.getUsername())).thenReturn(founded);
        User userFromDb = service.findUser("admin2");
        assertEquals(userFromDb.getUsername(), "admin2");
        assertNotNull(userFromDb.getId());
        assertEquals(userFromDb.getRole(), Role.USER);
    }
}