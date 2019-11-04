package by.jacviah.winery.dao.util.mapper;

import by.jacviah.winery.dao.entity.WineEntity;
import by.jacviah.winery.model.Wine;

public class WineMapper {
    public static WineEntity toEntity(Wine dto) {

        return WineEntity.WineEntityBuilder.aWineEntity()
                .withId(dto.getId())
                .withName(dto.getName())
                .withWinery(dto.getWinery())


                .build();
    }


    public static Wine toDTO(WineEntity entity) {
        return Wine.WineBuilder.aWine()
                .withId(entity.getId())

                .build();
    }
}
