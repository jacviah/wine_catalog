package by.jacviah.winery.sevice.impl;


import by.jacviah.winery.dao.SommDAO;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultSommServiceTest {
    @Mock
    SommDAO sommDao;
    @Mock
    UserDAO userDao;

    @InjectMocks
    DefaultSommService service;

    @Test
    public void testSetAsSomm() throws DaoException {
        User user = new User();
        user.setId(1);
        when(userDao.findUser("admin")).thenReturn(user);
        when(sommDao.setUserAsSommelier(1)).thenReturn(true);
        assertTrue(service.setUserAsSommelier("admin"));
    }
}
