package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.WineDAO;
import by.jacviah.winery.dao.impl.DefaultWineDAO;
import by.jacviah.winery.model.User;
import by.jacviah.winery.model.Wine;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.time.Instant;

public class DefaultWineDAOTest {
    WineDAO dao = DefaultWineDAO.getInstance();

    @Test
    public void testFindWine() throws Exception {
        Assert.assertNotNull(dao.findWine("wine", "winery"));
    }

    @Test
    public void testAddWine() throws Exception {
        String name = "wine" + Instant.now().toString();
        Wine wine = new Wine("Piedmont", "Merlot", name, "winery");
        Assert.assertTrue(dao.addWine(wine));
    }
}
