package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.entity.UserEntity;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.dao.util.EMUtil;
import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;
import by.jacviah.winery.model.UserDetail;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class DefaultUserDAOTest {
  UserDAO dao = DefaultUserDAO.getInstance();

    @Test
    public void testFindUser001() throws Exception {

        User user = dao.findUser("user");
        Assert.assertNotNull(user.getUuid());
    }

    @Test
    public void testAddUser001() throws Exception {
        User user = User.UserBuilder.anUser()
                .withUsername("gamer " + Instant.now().toString())
                .withPassword("1")
                .withRole(Role.USER)
                .withDetail(new UserDetail(null, "asdasdasd"))
                .build();
        dao.addUser(user);
        user = dao.findUser(user.getUsername());
        Assert.assertTrue(user.getRole().equals(Role.USER));
        Assert.assertTrue(user.getPassword().equals("1"));
        dao.removeUser(user);
    }

    @Test
    public void testAddUser002() throws Exception {
        User user = new User("user", "user");
        assertThrows(DaoException.class, () -> {
            dao.addUser(user);
        });

    }

    @Test
    public void testDeleteUser001() throws Exception {
        User user = User.UserBuilder.anUser()
                .withUsername("user_for_delete")
                .withPassword("1")
                .withRole(Role.USER)
                .withDetail(null)
                .build();
        dao.addUser(user);
        User fromDB = dao.findUser(user.getUsername());
        dao.removeUser(fromDB);
        Assert.assertNull(dao.findUser(user.getUsername()));
    }

    @Test
    public void cacheTest() {
        Session em = EMUtil.getSession().getSession();
        UserEntity user = em.get(UserEntity.class, 1L);

        user = EMUtil.getSession().get(UserEntity.class, 1L);
        em.close();
    }

}
