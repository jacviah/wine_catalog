package by.jacviah.winery.dao.util.mapper;

import by.jacviah.winery.dao.entity.CountryEntity;
import by.jacviah.winery.model.Country;

public class CountryMapper {
    public static CountryEntity toEntity(Country dto) {
        return CountryEntity.CountryEntityBuilder.aCountryEntity()
                .withId(dto.getId())
                .withName(dto.getName())
                .build();
    }

    public static Country toDTO(CountryEntity entity) {
        return new Country (entity.getId(), entity.getName());
    }
}
