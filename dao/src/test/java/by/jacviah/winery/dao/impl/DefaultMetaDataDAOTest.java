package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.MetaDataDAO;
import by.jacviah.winery.dao.config.DaoConfig;
import by.jacviah.winery.model.Country;
import by.jacviah.winery.model.Region;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
public class DefaultMetaDataDAOTest {

    @Autowired
    MetaDataDAO dao;

    @Test
    public void testGetCountries() {

        List<Country> countries = dao.getCountries();
        assertThat(countries, hasSize(12));
    }

    @Test
    public void testGetCountryRegions() {

        List<Region> regions = dao.getCountryRegions("Italy");
        assertThat(regions, hasSize(5));
        assertThat(regions, hasItems(new Region(2L, "Tuscany")));
    }

    @Test
    public void testGetGrapes() {

        List<Region> regions = dao.getCountryRegions("Italy");
        assertThat(regions, hasSize(5));
        assertThat(regions, hasItems(new Region(2L, "Tuscany")));
    }
}
