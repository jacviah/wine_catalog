package by.jacviah.winery.impl;

import by.jacviah.winery.User;
import by.jacviah.winery.ServiceFactory;
import by.jacviah.winery.UserService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class DefaultUserServiceTest {
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