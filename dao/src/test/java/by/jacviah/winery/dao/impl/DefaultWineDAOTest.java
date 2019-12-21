package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.WineDAO;
import by.jacviah.winery.dao.config.DaoConfig;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
public class DefaultWineDAOTest {

    @Autowired
    WineDAO dao;

   @Test
    public void testFindWine() throws Exception {
       Assert.assertNotNull(dao.findWine("wineItaly2", "winery"));
    }

    @Test
    @Transactional
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
        Assert.assertTrue(dao.addWine(wine));
    }

    @Test
    public void testGetWines() throws Exception {
        Assert.assertNotNull(dao.getWines());
        dao.getWines().stream().forEach(wine -> System.out.println(wine));
    }
}
