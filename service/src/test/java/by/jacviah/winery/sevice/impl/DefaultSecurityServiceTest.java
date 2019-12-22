package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class DefaultSecurityServiceTest {
    private static final Logger log = LoggerFactory.getLogger(DefaultSecurityServiceTest.class);

    @Mock
    UserDAO dao;

    @InjectMocks
    DefaultSecurityService service;

    @Test
    public void testLoginSuccess() {
        User user = User.UserBuilder.anUser()
                .withId(1L)
                .withUsername("user")
                .withPassword("123")
                .build();
        when(dao.findUser("user")).thenReturn(user);
        Assertions.assertTrue(service.login("user", "123").equals(user));
    }

    @Test
    public void testLoginUnsuccess01() {
        when(dao.findUser("user")).thenReturn(null);
        Assertions.assertNull(service.login("user", "123"));
    }

    @Test
    public void testLoginUnsuccess02() {
        User user = User.UserBuilder.anUser()
                .withId(1L)
                .withUsername("user")
                .withPassword("123")
                .build();
        when(dao.findUser("user")).thenReturn(user);
        Assertions.assertNull(service.login("user", "124"));
    }
}
