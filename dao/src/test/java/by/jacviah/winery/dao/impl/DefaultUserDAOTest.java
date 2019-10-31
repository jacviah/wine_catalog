package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.entity.GrapeEntity;
import by.jacviah.winery.dao.entity.RegionEntity;
import by.jacviah.winery.dao.entity.UserEntity;
import by.jacviah.winery.dao.entity.WineEntity;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.dao.impl.DefaultUserDAO;
import by.jacviah.winery.dao.util.EMUtil;
import by.jacviah.winery.model.User;
import org.junit.*;
import org.junit.Ignore;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;


public class DefaultUserDAOTest {
/*    UserDAO dao = DefaultUserDAO.getInstance();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAddUser001() throws Exception {
        User user = new User("gamer " + Instant.now().toString(), "qwerty");
        User result = dao.addUser(user);
        Assert.assertTrue(result.getId()>0);
    }

    @Test
    public void testAddUser002() throws Exception {

        User user = new User("user", "user");
        thrown.expect(DaoException.class);
        dao.addUser(user);
    }

    @Test
    public void testFindUser001() throws Exception {

        User user = dao.findUser("user");
        Assert.assertNotNull(user.getUuid());
    }

    @Test
    public void testDeleteUser001() throws Exception {
        dao.addUser(new User("user_for_delete", "1"));
        Assert.assertNotNull(dao.removeUser("user_for_delete")==true);
    }

    @Test
    public void testDeleteUser002() throws Exception {

        Assert.assertTrue(dao.removeUser("user_for_delete")==false);
    }*/

    @Test
    public void saveTest() {
        UserEntity user = UserEntity.UserEntityBuilder.anUserEntity()
                .withUsername("AAA")
                .withPassword("aaa")
                .withRole("admin")
                .withUuid(UUID.randomUUID().toString())
                .build();

        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.flush();
        em.getTransaction().commit();

/*        em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        user = em.find(UserEntity.class, user.getId());
        em.remove(user);
        em.getTransaction().commit();*/
    }
}
