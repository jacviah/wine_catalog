package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.*;
import by.jacviah.winery.dao.entity.BottleEntity;
import by.jacviah.winery.dao.entity.UserEntity;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.dao.util.EMUtil;
import by.jacviah.winery.dao.util.mapper.BottleMapper;
import by.jacviah.winery.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

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
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Bottle> getUserBottles(User user, int page) {
        List<Bottle> result = new ArrayList<>();
        int pageSize = 2;
        try (Session session = EMUtil.getSession()) {
            session.beginTransaction();
            List<BottleEntity> list = session.createQuery(
                    "select b " +
                            "from BottleEntity as b" +
                            " inner join b.user as u where u.id = :user", BottleEntity.class)
                    .setParameter("user", user.getId())
                    .setMaxResults(pageSize).setFirstResult(page * pageSize)
                    .getResultList();
            for (BottleEntity item : list) {
                result.add(BottleMapper.toDTO(item));
            }
            session.getTransaction().commit();
            return result;
        } catch (HibernateException e) {
            e.printStackTrace();
            return result;
        }
    }

    @Override
    public boolean removeBottle(Bottle dto) {
        BottleEntity bottle = BottleMapper.toEntity(dto);
        try (Session session = EMUtil.getSession()) {
            BottleEntity readBottle = session.get(BottleEntity.class, bottle.getId());
            session.beginTransaction();
            session.delete(readBottle);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }


}