package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.DataSource;
import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.dto.UserDTO;
import by.jacviah.winery.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class DefaultUserDAO implements UserDAO {

    private static final Logger log = LoggerFactory.getLogger(DefaultUserDAO.class);
    private final String url;
    private final String user;
    private final String pass;

    private DefaultUserDAO() {
        ResourceBundle resource = ResourceBundle.getBundle("db");
        url = resource.getString("url");
        user = resource.getString("user");
        pass = resource.getString("password");
    }


    private static class SingletonHolder {
        static final UserDAO HOLDER_INSTANCE = new DefaultUserDAO();
    }

    public static UserDAO getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }


    @Override
    public void init() {
    }

    @Override
    public List<User> getAll() throws IOException {
        return null;
    }

    @Override
    public UUID getUUID(User user) {
        return null;
    }

    private Connection getConnection() throws SQLException {
        return DataSource.getInstance().getConnection();
    }

    @Override
    public User addUser(User user) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into user(login, password) values (?,?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();

            return user;
        } catch (SQLException e) {
            log.error("fail to save salary:{}", user, e);
            throw new RuntimeException(e);
        }
    }




}
