package by.jacviah.winery.sevice.impl;

import by.jacviah.winery.dao.DAOProvider;
import by.jacviah.winery.dao.RecommendDAO;
import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Recommendation;
import by.jacviah.winery.sevice.RecommendService;

public class DeafaultRecommendService implements RecommendService {

    private DAOProvider provider = DAOProvider.getInstance();
    private RecommendDAO recDAO = provider.getRecommendDAO();

    @Override
    public boolean createRecommendation(Recommendation rec) throws DaoException {
        return recDAO.addRecommendation(rec);
    }
}
