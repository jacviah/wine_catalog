package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.MetaDataDAO;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import java.util.List;

public class DefaultMetaDataDAOTest {

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
    }

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
