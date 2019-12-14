package by.jacviah.winery.web;

import by.jacviah.winery.dao.MetaDataDAO;
import by.jacviah.winery.model.Country;
import by.jacviah.winery.sevice.MetadataService;
import by.jacviah.winery.web.controller.MetaController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class MetadataTest {

    @InjectMocks
    MetaController metaController;

    @Mock
    MetadataService service;

/*    @Test
    public void testGetCountries() {

        Country country1 = new Country(1L, "Greece");
        Country country2 = new Country(2L, "Italy");
        List<Country> countries = new ArrayList<>();
        countries.add(country1);
        countries.add(country2);

        when(service.getCountries()).thenReturn(countries);

        List<Country> result = metaController.getCountries();
        assertThat(result, hasSize(2));
        assertThat(result, hasItems(country1));
    }*/

}
