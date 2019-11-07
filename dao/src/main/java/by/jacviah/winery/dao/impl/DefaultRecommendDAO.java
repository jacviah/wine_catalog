package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.RecommendDAO;
import by.jacviah.winery.dao.entity.RecEntity;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.dao.util.EMUtil;
import by.jacviah.winery.dao.util.mapper.RecommendMapper;
import by.jacviah.winery.model.Recommendation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultRecommendDAO implements RecommendDAO {
    private static final Logger log = LoggerFactory.getLogger(DefaultWineDAO.class);

    private DefaultRecommendDAO() {
    }

    private static class SingletonHolder {
        static final RecommendDAO HOLDER_INSTANCE = new DefaultRecommendDAO();
    }

    public static RecommendDAO getInstance() {
        return DefaultRecommendDAO.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public boolean addRecommendation(Recommendation dto) throws DaoException {
        RecEntity rec = RecommendMapper.toEntity(dto);
        try (Session session = EMUtil.getSession()) {
            session.beginTransaction();
            session.save(rec);
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
}
