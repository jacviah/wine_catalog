package by.jacviah.winery.sevice.impl;

import static org.junit.jupiter.api.Assertions.*;

import by.jacviah.winery.dao.MetaDataDAO;
import by.jacviah.winery.dao.WineDAO;
import by.jacviah.winery.dao.config.DaoConfig;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Country;
import by.jacviah.winery.model.Grape;
import by.jacviah.winery.model.Region;
import by.jacviah.winery.model.Wine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@RunWith(JUnitPlatform.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class DefaultWineServiceTest {

    private static final Logger log = LoggerFactory.getLogger(DefaultWineServiceTest.class);

    @Mock
    WineDAO dao;
    @Mock
    MetaDataDAO meta;

    @InjectMocks
    DefaultWineService service;

    @Test
    public void testWineNotExist() {
        when(dao.findWine("wine", "winery")).thenReturn(null);
        Wine wine = service.findWine("wine", "winery");
        assertNull(wine);
    }

    @Test
    public void testWineExist() {
        Wine wine = Wine.WineBuilder.aWine()
                .withId(1L)
                .withCountry(new Country(1L, "Italy"))
                .withRegion(new Region(1L, "Piedmont"))
                .withGrape(new Grape(4L, "Syrah"))
                .withName("wine")
                .withWinery("winery")
                .withRate(2d)
                .build();
        when(dao.findWine("wine", "winery")).thenReturn(wine);
        Wine founded = service.findWine("wine", "winery");
        assertEquals(wine, founded);
    }

    @Test
    public void testAddWine() {
        Country country = new Country(1L, "Italy");
        Region region = new Region(1L, "Piedmont");
        Grape grape = new Grape(4L, "Syrah");
        Wine wine = Wine.WineBuilder.aWine()
                .withId(null)
                .withCountry(country)
                .withRegion(region)
                .withGrape(grape)
                .withName("wine")
                .withWinery("winery")
                .withRate(2d)
                .build();
        Wine wine2 = Wine.WineBuilder.aWine()
                .withId(2L)
                .withCountry(country)
                .withRegion(region)
                .withGrape(grape)
                .withName("wine")
                .withWinery("winery")
                .withRate(2d)
                .build();
        when(dao.addWine(wine)).thenReturn(true);
        when(dao.findWine(wine.getName(), wine.getWinery())).thenReturn(wine2);
        service.addWine(country, region, grape, "wine", "winery");

        Wine founded = service.findWine("wine", "winery");
        assertEquals(founded.getId(), wine2.getId());
        assertEquals(founded.getRegion(), wine.getRegion());
        assertEquals(founded.getName(), wine.getName());
    }
}
