package by.jacviah.winery.sevice.impl;

import static org.junit.jupiter.api.Assertions.*;

import by.jacviah.winery.dao.BottleDAO;
import by.jacviah.winery.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class DefaultBottleServiceTest {
    private static final Logger log = LoggerFactory.getLogger(DefaultBottleServiceTest.class);

    @Mock
    BottleDAO dao;

    @InjectMocks
    DefaultBottleService service;

    @Test
    public void testGetUserBottles() {
        Wine wine1 = Wine.WineBuilder.aWine()
                .withId(3L)
                .withCountry(new Country(1L, "Italy"))
                .withRegion(new Region(1L, "Piedmont"))
                .withGrape(new Grape(4L, "Syrah"))
                .withName("FFF")
                .withWinery("WWW")
                .withRate(2d)
                .build();
        Wine wine2 = Wine.WineBuilder.aWine()
                .withId(1L)
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
        Bottle bottle1 = Bottle.BottleBuilder.aBottle()
                .withWine(wine1)
                .withUser(user)
                .withIsDrunk(false)
                .withRate(Rate.EMPTY)
                .withYear(Year.now())
                .build();
        Bottle bottle2 = Bottle.BottleBuilder.aBottle()
                .withWine(wine2)
                .withUser(user)
                .withIsDrunk(false)
                .withRate(Rate.EMPTY)
                .withYear(Year.now())
                .build();
        Pageable pageable = PageRequest.of(0, 2);
        List<Bottle> list = new ArrayList<>();
        list.add(bottle1);
        list.add(bottle2);
        when(dao.getUserBottles(user,pageable)).thenReturn(list);
        List<Bottle> bottles = dao.getUserBottles(user, pageable);
        assertTrue(list.contains(bottle1));
        assertTrue(list.contains(bottle2));
    }
}
