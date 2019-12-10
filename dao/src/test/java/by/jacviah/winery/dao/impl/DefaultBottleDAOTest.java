package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.BottleDAO;
import by.jacviah.winery.dao.config.DaoConfig;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
public class DefaultBottleDAOTest {

    @Autowired
    BottleDAO dao;

    @Test
    @Transactional
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
            assertTrue(dao.addBottle(bottle));
    }

    @Test
    public void paginationTest() {
        User user = User.UserBuilder.anUser()
                .withId(1L)
                .withUsername("sommelier")
                .withRole(Role.SOMMELIER)
                .build();
        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
        Pageable secondPageWithTwoElements = PageRequest.of(1, 2);
        
        final List<Bottle> page0 = dao.getUserBottles(user, firstPageWithTwoElements);
        Assertions.assertTrue(page0.size()==2);
        final List<Bottle> page1 = dao.getUserBottles(user, secondPageWithTwoElements);
        Assertions.assertTrue(page1.size()==1);
    }
}
