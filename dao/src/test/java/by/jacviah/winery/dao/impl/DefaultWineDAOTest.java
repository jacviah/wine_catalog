package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.WineDAO;
import by.jacviah.winery.dao.entity.*;
import by.jacviah.winery.dao.util.EMUtil;
import by.jacviah.winery.model.User;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;

public class DefaultWineDAOTest {
    WineDAO dao = DefaultWineDAO.getInstance();

/*    @Test
    public void testFindWine() throws Exception {
        Assert.assertNotNull(dao.findWine("wine", "winery"));
    }

    @Test
    public void testAddWine() throws Exception {
        String name = "wine" + Instant.now().toString();
        Wine wine = new Wine("Piedmont", "Merlot", name, "winery");
        Assert.assertTrue(dao.addWine(wine));
    }
    @Test
    public void saveWineTest() {
        WineEntity wine = new WineEntity(null, new RegionEntity(1L, "Piedmont"),
                new GrapeEntity(4L, "Syrah"), "SD", "SF", 2d, new ArrayList<>());

        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(wine);
        em.flush();
        em.getTransaction().commit();

        em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        wine = em.find(WineEntity.class, wine.getId());
        em.remove(wine);
        em.getTransaction().commit();
    }

    @Test
    public void saveBottleTest() {
        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        WineEntity wine = em.find(WineEntity.class, 1L);
        UserEntity user = em.find(UserEntity.class, 1L);

        BottleEntity bottle = BottleEntity.BottleEntityBuilder.aBottleEntity()
                .withUser(user)
                .withWine(wine)
                .withRate(4)
                .withDate(LocalDate.now())
                .withYear("1988")
                .withIsDrunk(true)
                .build();
        em.persist(bottle);
        em.flush();
        em.getTransaction().commit();
    }*/

}
