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

public class DefaultBottleDAO implements BottleDAO {

    private static final Logger log = LoggerFactory.getLogger(DefaultBottleDAO.class);

    private DefaultBottleDAO() {
    }

    private static class SingletonHolder {
        static final BottleDAO HOLDER_INSTANCE = new DefaultBottleDAO();
    }

    public static BottleDAO getInstance() {
        return DefaultBottleDAO.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public boolean addBottle(Bottle bottle) throws DaoException {
        try (Session session = EMUtil.getSession()) {
            session.beginTransaction();
            session.save(BottleMapper.toEntity(bottle));
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