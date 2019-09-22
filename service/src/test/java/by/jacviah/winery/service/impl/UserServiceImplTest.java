package by.jacviah.winery.service.impl;

import by.jacviah.winery.model.User;
import by.jacviah.winery.service.ServiceFactory;
import by.jacviah.winery.service.UserService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jacviah on 23.09.2019.
 */
public class UserServiceImplTest {

    @Test
    public void testFindUser() throws Exception {
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();
        service.init();
        User user = new User("player", "123");
        Assert.assertEquals(service.findUser("player"), user);
    }

    @Test
    public void testCreateUser() throws Exception {
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();
        service.init();
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