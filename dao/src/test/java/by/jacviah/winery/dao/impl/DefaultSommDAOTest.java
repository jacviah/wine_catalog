package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.SommDAO;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.config.DaoConfig;
import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
public class DefaultSommDAOTest {
    @Autowired
    private SommDAO dao;

    @Autowired
    private UserDAO userDAO;

    @Test
    @Transactional
    public void testSetUserAsSommelier001() throws Exception {
        User user = User.UserBuilder.anUser()
                .withUsername("userAsSommelier")
                .withPassword("123")
                .withRole(Role.USER)
                .build();
        userDAO.addUser(user);
        User fromDB = userDAO.findUser(user.getUsername());
        Assert.assertTrue(dao.setRole(fromDB, Role.SOMMELIER));
    }
}
