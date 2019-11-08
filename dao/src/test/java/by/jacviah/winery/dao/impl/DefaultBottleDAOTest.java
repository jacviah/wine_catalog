package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.BottleDAO;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DefaultBottleDAOTest {

    BottleDAO dao = DefaultBottleDAO.getInstance();

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
            assertTrue(dao.addBottle(bottle));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void paginationTest() {
        User user = User.UserBuilder.anUser()
                .withId(1L)
                .withUsername("sommelier")
                .withRole(Role.SOMMELIER)
                .build();

        final List<Bottle> page0 = dao.getUserBottles(user, 0);
        Assertions.assertTrue(page0.size()==2);

        final List<Bottle> page1 = dao.getUserBottles(user, 1);
        Assertions.assertTrue(page1.size()==1);
    }
}
