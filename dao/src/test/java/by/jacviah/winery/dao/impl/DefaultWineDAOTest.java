package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.WineDAO;
import by.jacviah.winery.dao.entity.*;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.dao.util.EMUtil;
import by.jacviah.winery.model.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;

public class DefaultWineDAOTest {
    WineDAO dao = DefaultWineDAO.getInstance();

   @Test
    public void testFindWine() throws Exception {
        Assert.assertNotNull(dao.findWine("wine", "winery"));
    }

    /*    @Test
       public void testAddWine() throws Exception {
           String name = "wine" + Instant.now().toString();
           Wine wine = new Wine("Piedmont", "Merlot", name, "winery");
           Assert.assertTrue(dao.addWine(wine));
       }*/
    @Test
    public void saveWineTest() {
        Wine wine = Wine.WineBuilder.aWine()
                .withId(null)
                .withName("FFF")
                .withWinery("WWW")
                .withRate(2d)
                .build();
        Country country = new Country (1L, "Italy");
        Region region = new Region(1L, "", "Piedmont");
        Grape grape = new Grape(4L, "Syrah");
        try {
            dao.addWine(wine, region, country, grape);
        } catch (DaoException e){
            e.printStackTrace();
        }
    }

 /*    @Test
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
