package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.DataSource;
import by.jacviah.winery.dao.SommDAO;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    private Connection getConnection() throws SQLException {
        return DataSource.getInstance().getConnection();
    }
    @Override
    public boolean setUserAsSommelier(int user_id) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement set_user_as_sommelier = connection.prepareStatement("update " +
                     "user set role = ? WHERE id =?")) {
            set_user_as_sommelier.setString(1, Role.SOMMELIER.toString());
            set_user_as_sommelier.setInt(2, user_id);
            int i =  set_user_as_sommelier.executeUpdate();
            if (i>0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            log.error("sorry:{}", e);
            throw new DaoException(DaoException._SQL_ERROR);
        }
    }
}
