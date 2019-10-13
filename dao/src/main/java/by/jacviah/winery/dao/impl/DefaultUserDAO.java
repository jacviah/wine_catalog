package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.DataSource;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private Connection getConnection() throws SQLException {
        return DataSource.getInstance().getConnection();
    }

    @Override
    public User findUser(String login) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement find_user = connection.prepareStatement("select " +
                     "u.id, u.login, u.password, u.role, a.uuid from user u inner join auth_user a on u.id = a.auth_id where u.login = ?")) {

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
                log.info("user:{} not founded", user.toString());
                return null;
            }
            log.info("user:{} founded", user.toString());
            return user;
        } catch (SQLException e) {
            log.error("fail to find user:{}", login, e);
            throw new DaoException(DaoException._SQL_ERROR);
        }
    }

    @Override
    public UUID getUUID(String name) throws DaoException {
        return findUser(name).getUuid();
    }

    @Override
    public User addUser(User user) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement user_insert = connection.prepareStatement("insert into user(login, password, role) values (?,?,?)",
                     Statement.RETURN_GENERATED_KEYS);
             PreparedStatement auth_user_insert = connection.prepareStatement("insert into auth_user(auth_id,login, uuid) values (?,?,?)")) {

            connection.setAutoCommit(false);
            user_insert.setString(1, user.getUsername());
            user_insert.setString(2, user.getPassword());
            user_insert.setString(3, user.getRole().toString());
            user_insert.executeUpdate();
            ResultSet rs = user_insert.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);

            auth_user_insert.setString(1, String.valueOf(id));
            auth_user_insert.setString(2, user.getUsername());
            auth_user_insert.setString(3, user.getUuid().toString());
            auth_user_insert.executeUpdate();

            connection.commit();
            user.setId(id);
            return user;
        } catch (SQLIntegrityConstraintViolationException e) {
            log.info("user {} is exist", user.getUsername());
            throw new DaoException(DaoException._FAIL_TO_INSERT);
        } catch (SQLException e) {
            log.error("fail to save user:{}", user.toString(), e);
            throw new DaoException(DaoException._SQL_ERROR);
        }
    }

    @Override
    public boolean removeUser(String login) throws DaoException {
        try (Connection connection = getConnection();
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
        }
    }

}
