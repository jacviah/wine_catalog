package by.jacviah.winery.dao.util.mapper;

import by.jacviah.winery.dao.entity.RegionEntity;
import by.jacviah.winery.model.Country;
import by.jacviah.winery.model.Region;

public class RegionMapper {
    public static RegionEntity toEntity(Region dto, Country country) {
        return RegionEntity.RegionEntityBuilder.aRegionEntity()
                .withId(dto.getId())
                .withName(dto.getName())
                .withCountry(CountryMapper.toEntity(country))
                .build();
    }

    public static Region toDTO(RegionEntity entity) {
        return new Region (entity.getId(), entity.getName());
    }
}
