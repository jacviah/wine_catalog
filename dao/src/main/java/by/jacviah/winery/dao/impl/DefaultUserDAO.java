package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.entity.UserEntity;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.dao.DataSource;
import by.jacviah.winery.dao.util.EMUtil;
import by.jacviah.winery.dao.util.mapper.UserMapper;
import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.UUID;

public class DefaultUserDAO implements UserDAO {

    private static final Logger log = LoggerFactory.getLogger(DefaultUserDAO.class);

    private DefaultUserDAO() {
    }

    private static class SingletonHolder {
        static final UserDAO HOLDER_INSTANCE = new DefaultUserDAO();
    }

    public static UserDAO getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public User findUser(String login) throws DaoException {
/*        try (Connection connection = getConnection();
             PreparedStatement find_user = connection.prepareStatement("select " +
                     "u.id, u.login, u.password, u.role, a.uuid from user u inner join auth_user a on u.id = a.user_id where u.login = ?")) {

            User user = new User();
            find_user.setString(1, login);
            ResultSet rs = find_user.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setRole(Role.asRole(rs.getString(4)));
                user.setUuid(UUID.fromString(rs.getString(5)));
            } else {
                log.warn("user:{} not founded", user.toString());
                return null;
            }
            log.warn("user:{} founded", user.toString());
            return user;
        } catch (SQLException e) {
            log.error("fail to find user:{}", login, e);
            throw new DaoException(DaoException._SQL_ERROR);
        }*/
        return null;
    }

    @Override
    public UUID getUUID(String name) throws DaoException {
        return null; //findUser(name).getUuid();
    }

    @Override
    public boolean addUser(User user) throws DaoException {
        try (Session session = EMUtil.getSession()) {
            session.beginTransaction();
            session.save(UserMapper.toEntity(user));
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(String login) throws DaoException {
/*        try (Connection connection = getConnection();
             PreparedStatement delete_auth_user = connection.prepareStatement("delete from wine_catalog.auth_user where login = ?");
             PreparedStatement delete_user = connection.prepareStatement("delete from wine_catalog.user where login = ?")) {

            connection.setAutoCommit(false);
            delete_auth_user.setString(1, login);
            delete_user.setString(1, login);
            delete_auth_user.executeUpdate();
            int i = delete_user.executeUpdate();
            connection.commit();
            if (i>0) return true;
            return false;
        } catch (SQLException e) {
            log.error("fail to delete user:{}", login, e);
            throw new DaoException(DaoException._SQL_ERROR);
        }*/
        return false;
    }

}
