package by.jacviah.winery.dao.util.mapper;

import by.jacviah.winery.dao.entity.RecommendEntity;
import by.jacviah.winery.dao.entity.WineEntity;
import by.jacviah.winery.model.Recommendation;
import by.jacviah.winery.model.User;
import by.jacviah.winery.model.Wine;

import java.util.HashSet;
import java.util.Set;

public class RecommendMapper {
    public static RecommendEntity toEntity(Recommendation dto) {

        Set<WineEntity> target = new HashSet<>();
        Set<Wine> source = dto.getWines();
        for (Wine wine : source) {
            target.add(WineMapper.toEntity(wine));
        }

        return RecommendEntity.RecEntityBuilder.aRecEntity()
                .withId(dto.getId())
                .withMessage(dto.getMessage())
                .withAuthor(UserMapper.toEntity(dto.getSommelier()))
                .witSubscriber(UserMapper.toEntity(dto.getSubscriber()))
                .withWines(target)
                .build();
    }


    public static Recommendation toDTO(RecommendEntity entity) {
        Set<Wine> target = new HashSet<>();
        Set<WineEntity> source = entity.getWines();
        for (WineEntity wine : source) {
            target.add(WineMapper.toDTO(wine));
        }

        return Recommendation.RecommendationBuilder.aRecommendation()
                .withId(entity.getId())
                .withMessage(entity.getMessage())
                .withSommelier(UserMapper.toDTO(entity.getAuthor()))
                .withSubscriber(UserMapper.toDTO(entity.getSubscriber()))
                .withWines(target)
                .build();
    }
}
