package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.DataSource;
import by.jacviah.winery.dao.MetaDataDAO;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultMetaDataDAO implements MetaDataDAO {
    private static final Logger log = LoggerFactory.getLogger(DefaultUserDAO.class);

    private DefaultMetaDataDAO() {
    }

    private static class SingletonHolder {
        static final MetaDataDAO HOLDER_INSTANCE = new DefaultMetaDataDAO();
    }

    public static MetaDataDAO getInstance() {
        return DefaultMetaDataDAO.SingletonHolder.HOLDER_INSTANCE;
    }

    private Connection getConnection() throws SQLException {
        return DataSource.getInstance().getConnection();
    }

    public List<String> getCountryRegions(int country_id) throws DaoException {
        List<String> regions = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement select_regions = connection.prepareStatement("select" +
                     " name from region where country_id = ?")) {
            select_regions.setInt(1, country_id);
            ResultSet rs = select_regions.executeQuery();
            while (rs.next()) {
                regions.add(rs.getString(1));
            }
        } catch (SQLException e) {
            log.error("sorry:{}", e);
            throw new DaoException(DaoException._SQL_ERROR);
        }
        return regions;
    }

    @Override
    public int getGrapeIdByName(String grapeName) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement select_grape = connection.prepareStatement("select" +
                     " id from grapes where name = ?")) {
            select_grape.setString(1, grapeName);
            ResultSet rs = select_grape.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            log.error("sorry:{}", e);
            throw new DaoException(DaoException._SQL_ERROR);
        }
    }

    @Override
    public int getRegionIdByName(String regionName) throws DaoException {
        try (Connection connection = getConnection();
             PreparedStatement select_region = connection.prepareStatement("select" +
                     " id from region where name = ?")) {
            select_region.setString(1, regionName);
            ResultSet rs = select_region.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            log.error("sorry:{}", e);
            throw new DaoException(DaoException._SQL_ERROR);
        }
    }


}
