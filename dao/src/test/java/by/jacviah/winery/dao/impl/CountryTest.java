package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.entity.CountryEntity;
import by.jacviah.winery.dao.entity.RegionEntity;

import org.hibernate.Session;

import org.junit.AfterClass;
import org.junit.Test;
import by.jacviah.winery.dao.util.EMUtil;

import javax.persistence.EntityManager;

public class CountryTest {
    @Test
    public void saveTest() {
        CountryEntity country = new CountryEntity("SD");
        RegionEntity region = new RegionEntity(null, "SF", country);
        country.getRegions().add(region);

        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(country);
        em.flush();
        em.getTransaction().commit();

        em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        country = em.find(CountryEntity.class, country.getId());

        RegionEntity forDelete = country.getRegions().iterator().next();

        //country.getRegions().remove(forDelete);
        //em.remove(country);
        em.getTransaction().commit();
    }

    @AfterClass
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }
}

