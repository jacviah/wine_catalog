package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.RecommendDAO;
import by.jacviah.winery.dao.entity.RecommendEntity;
import by.jacviah.winery.dao.entity.UserEntity;
import by.jacviah.winery.dao.repository.RecommendRepository;
import by.jacviah.winery.dao.util.mapper.RecommendMapper;
import by.jacviah.winery.dao.util.mapper.UserMapper;
import by.jacviah.winery.model.Recommendation;
import by.jacviah.winery.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultRecommendDAO implements RecommendDAO {
    private static final Logger log = LoggerFactory.getLogger(DefaultWineDAO.class);
    @Autowired
    RecommendRepository repository;

    public DefaultRecommendDAO(RecommendRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean addRecommendation(Recommendation dto) {
        RecommendEntity entity = RecommendMapper.toEntity(dto);
        repository.save(entity);
        return (entity.getId()!=null);
    }

    @Override
    public List<Recommendation> findRecommendations(User author, User subscriber) {
        UserEntity authorEntity = UserMapper.toEntity(author);
        UserEntity subscriberEntity = UserMapper.toEntity(subscriber);
        List<Recommendation> result = repository.findAllByAuthorAndSubscriber(authorEntity, subscriberEntity)
                .stream().map(recommendEntity -> RecommendMapper.toDTO(recommendEntity)).collect(Collectors.toList());
        return result;
    }


}
