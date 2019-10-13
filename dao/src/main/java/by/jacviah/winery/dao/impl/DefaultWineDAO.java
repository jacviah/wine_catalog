package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.WineDAO;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.dao.DataSource;
import by.jacviah.winery.model.Bottle;
import by.jacviah.winery.model.Wine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultWineDAO implements WineDAO {

    private static final Logger log = LoggerFactory.getLogger(DefaultUserDAO.class);

    private DefaultWineDAO() {
    }

    private static class SingletonHolder {
        static final WineDAO HOLDER_INSTANCE = new DefaultWineDAO();
    }

    public static WineDAO getInstance() {
        return DefaultWineDAO.SingletonHolder.HOLDER_INSTANCE;
    }

    private Connection getConnection() throws SQLException {
        return DataSource.getInstance().getConnection();
    }

    @Override
    public Wine findWine(String name, String winery) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement find_wine = connection.prepareStatement("select " +
                     "* from wine w where w.name = ? and w.winery = ?")) {
            find_wine.setString(1, name);
            find_wine.setString(2, winery);
            ResultSet rs = find_wine.executeQuery();
            if (rs.next()) {
                Wine wine = new Wine();
                wine.setId(rs.getInt(1));
                wine.setRegion(rs.getString(2));
                wine.setGrape(rs.getString(3));
                wine.setName(rs.getString(4));
                wine.setWinery(rs.getString(5));
                wine.setRate(rs.getInt(6));
                log.warn("wine:{} founded", wine.toString());
                return wine;
            } else {
                log.warn("fail to find wine:{} {}", name, winery);
                return null;
            }
        } catch (SQLException e) {
            log.error("fail to find wine:{}", name, e);
            throw new DaoException(DaoException._SQL_ERROR);
        }
    }

    @Override
    public <List> Bottle getAllBottles(String name) throws DaoException {
        return null;
    }

    @Override
    public boolean addBottle(Bottle bottle, String login, int user_id) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement add_bottle = connection.prepareStatement("insert " +
                     "into bottle (wine_id, user_id, year)values (?, ?, ?)")) {
            Wine wine = findWine(bottle.getWine().getName(), bottle.getWine().getWinery());
            if (wine != null) {
                connection.setAutoCommit(false);
                add_bottle.setInt(1, wine.getId());
                add_bottle.setInt(2, user_id);
                add_bottle.setString(3, bottle.getYear().toString());
                int i = add_bottle.executeUpdate();
                connection.commit();
                if (i>0) return true;
                return false;
            }
            log.warn("fail to find bottle:{} {}", bottle.getWine().toString(), bottle.getYear());
            return false;

        } catch (SQLException e) {
            log.error("fail to find bottle:{} {}", bottle.getWine().toString(), bottle.getYear());
            throw new DaoException(DaoException._SQL_ERROR);
        }
    }
}
