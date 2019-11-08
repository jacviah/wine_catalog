package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.*;
import by.jacviah.winery.dao.entity.WineEntity;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.dao.util.EMUtil;
import by.jacviah.winery.dao.util.mapper.BottleMapper;
import by.jacviah.winery.dao.util.mapper.WineMapper;
import by.jacviah.winery.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class DefaultWineDAO implements WineDAO {

    private static final Logger log = LoggerFactory.getLogger(DefaultWineDAO.class);

    private DefaultWineDAO() {
    }

    private static class SingletonHolder {
        static final WineDAO HOLDER_INSTANCE = new DefaultWineDAO();
    }

    public static WineDAO getInstance() {
        return DefaultWineDAO.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public Wine findWine(String name, String winery) {
        try (Session session = EMUtil.getSession()) {
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<WineEntity> criteria = cb.createQuery(WineEntity.class);
            Root<WineEntity> root = criteria.from(WineEntity.class);

            Predicate predicate = cb.and(
                    cb.equal(root.get("name"), name),
                    cb.equal(root.get("winery"), winery)
            );
            criteria.select(root).where(predicate);

            WineEntity wine = session.createQuery(criteria).getSingleResult();
            session.close();
            return WineMapper.toDTO(wine);
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addWine(Wine wine) throws DaoException {
        try (Session session = EMUtil.getSession()) {
            session.beginTransaction();
            session.save(WineMapper.toEntity(wine));
            session.getTransaction().commit();
            return true;
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            throw new DaoException(4);
        }catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }
}
