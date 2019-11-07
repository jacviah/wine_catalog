package by.jacviah.winery.dao.util.mapper;

import by.jacviah.winery.dao.entity.RecEntity;
import by.jacviah.winery.dao.entity.WineEntity;
import by.jacviah.winery.model.Recommendation;
import by.jacviah.winery.model.Wine;

import java.util.HashSet;
import java.util.Set;

public class RecommendMapper {
    public static RecEntity toEntity(Recommendation dto) {

        Set<WineEntity> target = new HashSet<>();
        Set<Wine> source = dto.getWines();
        for (Wine wine : source) {
            target.add(WineMapper.toEntity(wine));
        }

        return RecEntity.RecEntityBuilder.aRecEntity()
                .withId(dto.getId())
                .withMessage(dto.getMessage())
                .withSommelier(UserMapper.toEntity(dto.getSommelier()))
                .withWines(target)
                .build();
    }


    public static Recommendation toDTO(RecEntity entity) {
        Set<Wine> target = new HashSet<>();
        Set<WineEntity> source = entity.getWines();
        for (WineEntity wine : source) {
            target.add(WineMapper.toDTO(wine));
        }

        return Recommendation.RecommendationBuilder.aRecommendation()
                .withId(entity.getId())
                .withMessage(entity.getMessage())
                .withSommelier(UserMapper.toDTO(entity.getSommelier()))
                .withWines(target)
                .build();
    }
}
