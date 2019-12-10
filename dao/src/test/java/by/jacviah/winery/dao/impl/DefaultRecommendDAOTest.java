package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.RecommendDAO;
import by.jacviah.winery.dao.config.DaoConfig;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
public class DefaultRecommendDAOTest {
    @Autowired
    RecommendDAO dao;

    @Test
    @Transactional
    public void saveRecommendationTest() {
        User sommelier = User.UserBuilder.anUser()
                .withId(1L)
                .withUsername("sommelier")
                .withRole(Role.SOMMELIER)
                .withDetail(null)
                .build();
        Wine wine1 = Wine.WineBuilder.aWine()
                .withId(2L)
                .withCountry(new Country(1L, "Italy"))
                .withRegion(new Region(1L, "Piedmont"))
                .withGrape(new Grape(1L, "Cabernet Sauvignon"))
                .withName("wine")
                .withWinery("winery")
                .build();
        Wine wine2 = Wine.WineBuilder.aWine()
                .withId(1L)
                .withCountry(new Country(1L, "Italy"))
                .withRegion(new Region(1L, "Piedmont"))
                .withGrape(new Grape(1L, "Cabernet Sauvignon"))
                .withName("wine")
                .withWinery("chateau")
                .build();
        Set<Wine> wines = new HashSet<>();
        wines.add(wine1);
        wines.add(wine2);
        Recommendation rec = Recommendation.RecommendationBuilder.aRecommendation()
                .withMessage("test message")
                .withSommelier(sommelier)
                .withWines(wines)
                .build();
            Assertions.assertTrue(dao.addRecommendation(rec));
    }
}
