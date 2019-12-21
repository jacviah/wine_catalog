package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.config.DaoConfig;
import by.jacviah.winery.dao.entity.UserEntity;
import by.jacviah.winery.dao.util.EMUtil;
import by.jacviah.winery.model.Country;
import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;
import by.jacviah.winery.model.UserDetail;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
public class DefaultUserDAOTest {

    @Autowired
    private UserDAO dao;

    @Test
    @Transactional
    public void testAddUser001() {
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
    }

    @Test
    @Transactional
    public void testAddUser002() {
        User user = new User("user", "user");
        assertThrows(DataIntegrityViolationException.class, () -> {
            dao.addUser(user);
        });
    }

    @Test
    @Transactional
    public void getUsersBySommelier001() {
        List<User> users1 = dao.getUsersBySommelier(1L);
        assertThat(users1, hasSize(1));
        List<User> users2 = dao.getUsersBySommelier(2L);
        assertThat(users2, hasSize(1));
        List<User> users3 = dao.getUsersBySommelier(3L);
        assertThat(users3, hasSize(0));
    }

    @Test
    @Transactional
    public void getUsersByRole001() {
        List<User> users1 = dao.getUsersByRole("user");
        assertThat(users1, hasSize(2));
        List<User> users2 = dao.getUsersByRole("sommelier");
        assertThat(users2, hasSize(2));
        List<User> users3 = dao.getUsersByRole("a");
        assertThat(users3, hasSize(0));
    }

    @Test
    @Transactional
    public void setUsersSommelier() {
        Assert.assertTrue(dao.getUsersSommelier(3L).equals(dao.findUser("sommelier")));
        dao.setSommelier(3L, 2L);
        Assert.assertTrue(dao.getUsersSommelier(3L).equals(dao.findUser("expert")));
    }

    @Test
    @Transactional
    public void testDeleteUser001() {
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

