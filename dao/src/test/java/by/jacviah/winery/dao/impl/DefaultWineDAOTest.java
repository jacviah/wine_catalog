package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.WineDAO;
import by.jacviah.winery.dao.entity.GrapeEntity;
import by.jacviah.winery.dao.entity.RegionEntity;
import by.jacviah.winery.dao.entity.WineEntity;
import by.jacviah.winery.dao.util.EMUtil;
import by.jacviah.winery.model.User;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.time.Instant;
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
    }*/

    @Test
    public void saveTest() {
        WineEntity wine = new WineEntity(null, new RegionEntity(1L, "Piedmont"),
                new GrapeEntity(4L, "Syrah"), "SD", "SF", 2, new ArrayList<>());

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
}
