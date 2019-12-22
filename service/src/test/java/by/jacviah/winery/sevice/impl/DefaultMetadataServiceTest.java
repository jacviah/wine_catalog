package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.MetaDataDAO;
import by.jacviah.winery.model.Country;
import by.jacviah.winery.model.Region;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class DefaultMetadataServiceTest {
    private static final Logger log = LoggerFactory.getLogger(DefaultMetadataServiceTest.class);

    @Mock
    MetaDataDAO dao;

    @InjectMocks
    DefaultMetadataService service;

    @Test
    public void testGetCountries() {
        Country country1 = new Country(1L, "Italy");
        Country country2 = new Country(2L, "France");
        Country country3 = new Country(3L, "Germany");
        List<Country> list = Arrays.asList(new Country[]{country1, country2, country3});
        when(dao.getCountries()).thenReturn(list);
        List<Country> result = service.getCountries();
        assertThat(result, hasSize(3));
        assertTrue(result.contains(country1));
        assertTrue(result.contains(country2));
        assertTrue(result.contains(country3));
    }

    @Test
    public void testGetRegionByName() {
        Region region = new Region(1L, "Sicily");
        when(dao.findRegion("Sicily")).thenReturn(region);
        Region result = service.getRegion("Sicily");
        assertTrue(result.equals(region));
    }
}