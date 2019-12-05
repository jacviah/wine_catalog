package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.entity.UserEntity;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.dao.repository.UserRepository;
import by.jacviah.winery.dao.util.EMUtil;
import by.jacviah.winery.dao.util.mapper.UserMapper;
import by.jacviah.winery.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.NoResultException;
import java.util.List;

public class DefaultUserDAO implements UserDAO {

    private static final Logger log = LoggerFactory.getLogger(DefaultUserDAO.class);

    private final UserRepository repository;

    public DefaultUserDAO(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User findUser(String login) {
        final UserEntity entity = repository.findByUsername(login);
        return UserMapper.toDTO(entity);
    }

/*
    @Override
    public User findUser(String login) {
        try (Session session = EMUtil.getSession()) {
            session.beginTransaction();
            Query<UserEntity> query = session.
                    createQuery("from UserEntity where username = :name", UserEntity.class)
                    .setCacheable(true);
            query.setParameter("name", login);
            User result = UserMapper.toDTO(query.getSingleResult());
            session.getTransaction().commit();
            session.close();
            return result;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addUser(User user) throws DaoException {
        try (Session session = EMUtil.getSession()) {
            session.beginTransaction();
            session.save(UserMapper.toEntity(user));
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

    @Override
    public boolean removeUser(User user) {
        try (Session session = EMUtil.getSession()) {
            UserEntity readUser = session.get(UserEntity.class, user.getId());
            session.beginTransaction();
            session.delete(readUser);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }
*/

}
