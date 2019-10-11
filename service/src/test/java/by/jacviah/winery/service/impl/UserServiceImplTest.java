package by.jacviah.winery.service.impl;

import by.jacviah.winery.model.User;
import by.jacviah.winery.service.ServiceFactory;
import by.jacviah.winery.service.UserService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {
    @Ignore
    @Test
    public void testFindUser() throws Exception {
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();
        User user = new User("gamer", "qwerty");
        Assert.assertEquals(service.findUser("gamer"), user);
    }
    @Ignore
    @Test
    public void testCreateUser() throws Exception {
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();
        Assert.assertNull(service.createUser("player", "123"));
    }

    @Ignore
    @Test
    public void testLogin() throws Exception {

    }

    @Ignore
    @Test
    public void testInit() throws Exception {

    }
}