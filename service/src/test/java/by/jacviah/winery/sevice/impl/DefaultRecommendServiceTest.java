package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.RecommendDAO;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.model.Recommendation;
import by.jacviah.winery.model.User;
import by.jacviah.winery.model.Wine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class DefaultRecommendServiceTest {
    private static final Logger log = LoggerFactory.getLogger(DefaultSecurityServiceTest.class);

    @Mock
    UserDAO userDao;
    @Mock
    RecommendDAO dao;

    @InjectMocks
    DefaultRecommendService service;

    @Test
    @Transactional
    public void testCreateRecommendation() {
        Wine wine1 = Wine.WineBuilder.aWine()
                .withId(null)
                .build();
        Wine wine2 = Wine.WineBuilder.aWine()
                .withId(null)
                .build();
        User sommelier = User.UserBuilder.anUser()
                .withId(1L)
                .build();
        User subscriber = User.UserBuilder.anUser()
                .withId(1L)
                .build();
        Recommendation rec = Recommendation.RecommendationBuilder.aRecommendation()
                .withWines(Arrays.asList(new Wine[]{wine1, wine2}).stream().collect(Collectors.toSet()))
                .withMessage("test recommendation")
                .withSubscriber(subscriber)
                .withSommelier(sommelier)
                .build();
        when(dao.addRecommendation(rec)).thenReturn(true);
        Assertions.assertTrue(service.createRecommendation(rec)==true);
    }

    @Test
    public void testGetUsersRecommendations() {
        Wine wine1 = Wine.WineBuilder.aWine()
                .withId(1L)
                .build();
        Wine wine2 = Wine.WineBuilder.aWine()
                .withId(2L)
                .build();
        User sommelier = User.UserBuilder.anUser()
                .withId(1L)
                .withUsername("sommelier")
                .build();
        User subscriber = User.UserBuilder.anUser()
                .withId(2L)
                .withUsername("subscriber")
                .build();
        Recommendation rec1 = Recommendation.RecommendationBuilder.aRecommendation()
                .withWines(Arrays.asList(new Wine[]{wine1}).stream().collect(Collectors.toSet()))
                .withSubscriber(subscriber)
                .withSommelier(sommelier)
                .build();
        Recommendation rec2 = Recommendation.RecommendationBuilder.aRecommendation()
                .withWines(Arrays.asList(new Wine[]{wine1, wine2}).stream().collect(Collectors.toSet()))
                .withSubscriber(subscriber)
                .withSommelier(sommelier)
                .build();
        List<Recommendation> list = Arrays.asList(new Recommendation[]{rec1, rec2}).stream().collect(Collectors.toList());
        when(userDao.getUsersSommelier(subscriber.getId())).thenReturn(sommelier);
        when(userDao.findUser(subscriber.getId())).thenReturn(subscriber);
        when(dao.findRecommendations(sommelier, subscriber)).thenReturn(list);
        Assertions.assertTrue(service.findUsersRecommendation(subscriber.getId()).equals(list));
    }
}
