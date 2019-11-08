package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.WineDAO;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.Year;

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
                .withCountry(new Country (1L, "Italy"))
                .withRegion(new Region(1L, "Piedmont"))
                .withGrape(new Grape(4L, "Syrah"))
                .withName("FFF")
                .withWinery("WWW")
                .withRate(2d)
                .build();
        try {
            dao.addWine(wine);
        } catch (DaoException e){
            e.printStackTrace();
        }
    }
}
