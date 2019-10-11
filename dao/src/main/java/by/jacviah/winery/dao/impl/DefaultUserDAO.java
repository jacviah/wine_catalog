package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.DataSource;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
    public User findUser(String login) throws IOException {
        try (Connection connection = getConnection();
             PreparedStatement find_user = connection.prepareStatement("select " +
                     "u.login, u.role, a.uuid from user u inner join auth_user a on u.id = a.auth_id where u.login = ?")) {

            User user = new User();
            find_user.setString(1, login);

            ResultSet rs = find_user.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString(1));
                user.setRole(Role.asRole(rs.getString(2)));
                user.setUuid(UUID.fromString(rs.getString(3)));
            } else {
               return null;
            }
            log.info("user:{} founded", user.toString());
            return user;
        } catch (SQLException e) {
            log.error("fail to find user:{}", login, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public UUID getUUID(User user) {
        return null;
    }

    @Override
    public User addUser(User user) {
        try (Connection connection = getConnection();
             PreparedStatement user_insert = connection.prepareStatement("insert into user(login, password, role) values (?,?,?)",
                     Statement.RETURN_GENERATED_KEYS);
             PreparedStatement auth_user_insert = connection.prepareStatement("insert into auth_user(auth_id,login, uuid) values (?,?,?)")) {

            connection.setAutoCommit(false);
            user_insert.setString(1, user.getUsername());
            user_insert.setString(2, user.getPassword());
            user_insert.setString(3, user.getRole().toString());
            int id = user_insert.executeUpdate();
            ResultSet rs = user_insert.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            System.out.println(id);
            auth_user_insert.setString(1, String.valueOf(id));
            auth_user_insert.setString(2, user.getUsername());
            auth_user_insert.setString(3, user.getUuid().toString());
            auth_user_insert.executeUpdate();

            connection.commit();
            user.setId(id);
            return user;
        } catch (SQLIntegrityConstraintViolationException e) {
            log.info("user {} is exist", user.getUsername());
            return null;
        } catch (SQLException e) {
            log.error("fail to save user:{}", user.toString(), e);
            throw new RuntimeException(e);
        }
    }


}
