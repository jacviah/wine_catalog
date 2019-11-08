package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.SommDAO;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class DefaultSommServiceTest {
    @Mock
    SommDAO sommDao;
    @Mock
    UserDAO userDao;

    @InjectMocks
    DefaultSommService service;

    @Test
    public void testSetAsSomm() {
        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        when(userDao.findUser("admin")).thenReturn(user);
        when(sommDao.setUserAsSommelier(user)).thenReturn(true);
        assertTrue(service.setUserAsSommelier("admin"));
    }
}
