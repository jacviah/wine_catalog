package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.BottleDAO;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.Year;

public class DefaultBottleDAOTest {

    BottleDAO dao = DefaultBottleDAO.getInstance();

/*    @Test
    public void testFindWine() throws Exception {
        Assert.assertNotNull(dao.findWine("wine", "winery"));
    }*/

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
