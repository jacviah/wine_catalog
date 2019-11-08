package by.jacviah.winery.sevice.impl;

import static org.junit.jupiter.api.Assertions.*;

import by.jacviah.winery.dao.MetaDataDAO;
import by.jacviah.winery.dao.WineDAO;
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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
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
        Wine wine = Wine.WineBuilder.aWine()
                .withId(null)
                .withCountry(new Country(1L, "Italy"))
                .withRegion(new Region(1L, "Piedmont"))
                .withGrape(new Grape(4L, "Syrah"))
                .withName("wine")
                .withWinery("winery")
                .withRate(2d)
                .build();
        Wine wine2 = Wine.WineBuilder.aWine()
                .withId(2L)
                .withCountry(new Country(1L, "Italy"))
                .withRegion(new Region(1L, "Piedmont"))
                .withGrape(new Grape(4L, "Syrah"))
                .withName("wine")
                .withWinery("winery")
                .withRate(2d)
                .build();
        try {
            when(meta.findCountry("Italy")).thenReturn(new Country(1L, "Italy"));
            when(meta.findRegion("Piedmont")).thenReturn(new Region(1L, "Piedmont"));
            when(meta.findGrape("Syrah")).thenReturn(new Grape(4L, "Syrah"));
            when(dao.addWine(wine)).thenReturn(true);
            when(dao.findWine(wine.getName(), wine.getWinery())).thenReturn(wine2);
            service.addWine("Italy", "Piedmont", "Syrah", "wine", "winery");
        } catch (DaoException e) {
            log.error(" testAddWine() - DaoException");
        }

        Wine founded = service.findWine("wine", "winery");
        assertEquals(founded.getId(), wine2.getId());
        assertEquals(founded.getRegion(), wine.getRegion());
        assertEquals(founded.getName(), wine.getName());
    }
}
