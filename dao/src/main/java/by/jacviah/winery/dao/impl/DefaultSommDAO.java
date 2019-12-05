package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.SommDAO;
import by.jacviah.winery.dao.util.EMUtil;
import by.jacviah.winery.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSommDAO implements SommDAO {
    private static final Logger log = LoggerFactory.getLogger(DefaultUserDAO.class);

    private DefaultSommDAO() {
    }


    private static class SingletonHolder {
        static final SommDAO HOLDER_INSTANCE = new DefaultSommDAO();
    }

    public static SommDAO getInstance() {
        return DefaultSommDAO.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public boolean setUserAsSommelier(User user) {
        try (Session session = EMUtil.getSession()) {
            session.beginTransaction();
            session.createQuery("update UserEntity e set e.role = 'sommelier' where login = :name")
                    .setParameter("name", user.getUsername())
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }
}
