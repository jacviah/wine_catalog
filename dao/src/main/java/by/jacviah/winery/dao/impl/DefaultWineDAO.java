package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.*;
import by.jacviah.winery.dao.exception.DaoException;
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
    public boolean addWine(Wine wine) throws DaoException {
        DAOProvider provider = DAOProvider.getInstance();
        MetaDataDAO metaDAO = provider.getMetaDAO();
        try (Connection connection = getConnection();
             PreparedStatement add_wine = connection.prepareStatement("insert " +
                     "into wine (region_id, grapes_id, name, winery) values (?, ?, ?, ?)")) {
            add_wine.setInt(1, metaDAO.getRegionIdByName(wine.getRegion()));
            add_wine.setInt(2, metaDAO.getGrapeIdByName(wine.getGrape()));
            add_wine.setString(3, wine.getName());
            add_wine.setString(4, wine.getWinery());
            int i = add_wine.executeUpdate();

            if (i > 0) return true;
            log.warn("not added wine:{} {}", wine.getName(), wine.getWinery());
            return false;
        } catch (SQLException e) {
            log.warn("fail to add wine:{} {}", wine.getName(), wine.getWinery());
            throw new DaoException(DaoException._SQL_ERROR);
        }
    }
}
