package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.MetaDataDAO;
import by.jacviah.winery.dao.impl.DefaultMetaDataDAO;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DefaultMetaDataDAOTest {

    MetaDataDAO dao = DefaultMetaDataDAO.getInstance();

    @Test
    public void testGetCountryRegions() throws Exception {

        List<String> regions = dao.getCountryRegions(1);
        assertThat(regions, hasSize(5));
        assertThat(regions, hasItems("Piedmont"));
    }

    @Test
    public void testGetRegionIdByName() throws Exception {

        int id = dao.getRegionIdByName("Piedmont");
        Assert.assertTrue(id==1);
    }

    @Test
    public void testGetGrapeIdByName() throws Exception {

        int id = dao.getGrapeIdByName("Merlot");
        Assert.assertTrue(id==3);
    }
}
