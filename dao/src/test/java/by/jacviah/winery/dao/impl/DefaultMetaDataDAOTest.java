package by.jacviah.winery.dao.impl;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

import by.jacviah.winery.dao.entity.CountryEntity;
import by.jacviah.winery.dao.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultMetaDataDAOTest {

    @Autowired
    UserRepository countryRepository;

/*      @Test
    public void testGetCountryRegions() throws Exception {

        List<CountryEntity> regions = countryRepository.findByName("Italy");
        assertThat(regions, hasSize(12));
    }

  MetaDataDAO dao = DefaultMetaDataDAO.getInstance();

    @Test
    public void testGetCountries() throws Exception {

        List<String> countries = dao.getCountries();
        assertThat(countries, hasSize(12));
        assertThat(countries, hasItems("Italy"));
    }

    @Test
    public void testGetCountryRegions() throws Exception {

        List<String> regions = dao.getCountryRegions("Italy");
        assertThat(regions, hasSize(5));
        assertThat(regions, hasItems("Tuscany"));
    }*/

/*    @Test
    public void testGetRegionIdByName() throws Exception {

        int id = dao.getRegionIdByName("Piedmont");
        Assert.assertTrue(id==1);
    }

    @Test
    public void testGetGrapeIdByName() throws Exception {

        int id = dao.getGrapeIdByName("Merlot");
        Assert.assertTrue(id==3);
    }*/
}
