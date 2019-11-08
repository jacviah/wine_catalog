package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.SommDAO;
import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class DefaultSommDAOTest {
    SommDAO dao = DefaultSommDAO.getInstance();

    @Test
    public void testSetUserAsSommelier001() throws Exception {
        User user = User.UserBuilder.anUser()
                .withId(1L)
                .withUsername("sommelier")
                .withRole(Role.SOMMELIER)
                .withDetail(null)
                .build();
        Assert.assertTrue(dao.setUserAsSommelier(user));
    }
}
