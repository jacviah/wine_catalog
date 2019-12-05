package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.MetaDataDAO;
import by.jacviah.winery.dao.entity.CountryEntity;
import by.jacviah.winery.dao.entity.GrapeEntity;
import by.jacviah.winery.dao.entity.RegionEntity;
import by.jacviah.winery.dao.util.EMUtil;
import by.jacviah.winery.dao.util.mapper.CountryMapper;
import by.jacviah.winery.dao.util.mapper.GrapeMapper;
import by.jacviah.winery.dao.util.mapper.RegionMapper;
import by.jacviah.winery.model.Country;
import by.jacviah.winery.model.Grape;
import by.jacviah.winery.model.Region;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DefaultMetaDataDAO implements MetaDataDAO {
    private static final Logger log = LoggerFactory.getLogger(DefaultUserDAO.class);

    private DefaultMetaDataDAO() {
    }

    private static class SingletonHolder {
        static final MetaDataDAO HOLDER_INSTANCE = new DefaultMetaDataDAO();
    }

    public static MetaDataDAO getInstance() {
        return DefaultMetaDataDAO.SingletonHolder.HOLDER_INSTANCE;
    }

    public List<String> getCountries() {
        List<String> countries = new ArrayList<>();
        try (Session session = EMUtil.getSession()) {
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<String> query = cb.createQuery(String.class);
            Root<CountryEntity> root = query.from(CountryEntity.class);
            query.select(cb.construct(String.class, root.get("name")));
            countries = session.createQuery(query).getResultList();
        } catch (HibernateException e) {
            log.error("HibernateException in method getCountries()", e);
        }
        return countries;
    }

    public Country findCountry(String name) {

        try (Session session = EMUtil.getSession()) {
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CountryEntity> query = cb.createQuery(CountryEntity.class);
            Root<CountryEntity> root = query.from(CountryEntity.class);
            query.select(root)
                    .where(cb.equal(root.get("name"), name));
            return CountryMapper.toDTO(session.createQuery(query).getSingleResult());
        } catch (HibernateException e) {
            log.error("HibernateException in method findCountry(String name)", e);
            return null;
        }
    }

    public List<String> getCountryRegions(String countryName) {
        List<RegionEntity> regions = new ArrayList<>();
        try (Session session = EMUtil.getSession()) {
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<RegionEntity> query = cb.createQuery(RegionEntity.class);
            Root<CountryEntity> country = query.from(CountryEntity.class);
            Join<CountryEntity, RegionEntity> regionEntityJoin = country.join("regions");
            query.select(regionEntityJoin).where(cb.equal(country.get("name"), countryName));
            regions = session.createQuery(query).getResultList();
        } catch (HibernateException e) {
            log.error("HibernateException in method getCountryRegions(String countryName)", e);
        }
        List<String> strings = new ArrayList<>();
        for (RegionEntity r : regions) {
            strings.add(r.getName());
        }
        return strings;
    }

    public Region findRegion(String name) {
        try (Session session = EMUtil.getSession()) {
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<RegionEntity> query = cb.createQuery(RegionEntity.class);
            Root<RegionEntity> root = query.from(RegionEntity.class);
            query.select(root)
                    .where(cb.equal(root.get("name"), name));
            return RegionMapper.toDTO(session.createQuery(query).getSingleResult());
        } catch (HibernateException e) {
            log.error("HibernateException in method findRegion(String name)", e);
            return null;
        }
    }

    @Override
    public Grape findGrape(String name) {
        try (Session session = EMUtil.getSession()) {
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<GrapeEntity> query = cb.createQuery(GrapeEntity.class);
            Root<GrapeEntity> root = query.from(GrapeEntity.class);
            query.select(root)
                    .where(cb.equal(root.get("name"), name));
            return GrapeMapper.toDTO(session.createQuery(query).getSingleResult());
        } catch (HibernateException e) {
            log.error("HibernateException in method findGrape(String name)", e);
            return null;
        }
    }


}
