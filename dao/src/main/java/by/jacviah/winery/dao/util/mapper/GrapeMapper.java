package by.jacviah.winery.dao.util.mapper;

import by.jacviah.winery.dao.entity.GrapeEntity;
import by.jacviah.winery.model.Grape;

public class GrapeMapper {
    public static GrapeEntity toEntity(Grape dto) {
        return GrapeEntity.GrapeEntityBuilder.aGrapeEntity()
                .withId(dto.getId())
                .withName(dto.getName())
                .build();
    }

    public static Grape toDTO(GrapeEntity entity) {
        return new Grape (entity.getId(), entity.getName());
    }
}
