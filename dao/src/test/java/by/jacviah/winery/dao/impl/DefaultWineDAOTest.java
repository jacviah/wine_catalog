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
import java.time.Year;
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

     @Test
    public void saveBottleTest() {
         Wine wine = Wine.WineBuilder.aWine()
                 .withId(2L)
                 .withCountry(new Country(1L, "Italy"))
                 .withRegion(new Region(1L, "Piedmont"))
                 .withGrape(new Grape(4L, "Syrah"))
                 .withName("FFF")
                 .withWinery("WWW")
                 .withRate(2d)
                 .build();
         User user = User.UserBuilder.anUser()
                 .withId(2L)
                 .withUsername("user")
                 .withRole(Role.USER)
                 .build();
         Bottle bottle = Bottle.BottleBuilder.aBottle()
                 .withWine(wine)
                 .withUser(user)
                 .withIsDrunk(false)
                 .withRate(Rate.EMPTY)
                 .withYear(Year.now())
                 .build();
         try {
             dao.addBottle(bottle);
         } catch (DaoException e) {
             e.printStackTrace();
         }
     }
}
