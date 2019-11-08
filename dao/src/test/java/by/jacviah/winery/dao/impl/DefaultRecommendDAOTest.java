package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.RecommendDAO;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class DefaultRecommendDAOTest {
    RecommendDAO dao = DefaultRecommendDAO.getInstance();

    @Test
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

        try {
            Assertions.assertTrue(dao.addRecommendation(rec));
        } catch (DaoException e){
            e.printStackTrace();
        }
    }
}
