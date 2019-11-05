package by.jacviah.winery.dao.util.mapper;

import by.jacviah.winery.dao.entity.CountryEntity;
import by.jacviah.winery.dao.entity.GrapeEntity;
import by.jacviah.winery.dao.entity.RegionEntity;
import by.jacviah.winery.dao.entity.WineEntity;
import by.jacviah.winery.model.Country;
import by.jacviah.winery.model.Grape;
import by.jacviah.winery.model.Region;
import by.jacviah.winery.model.Wine;

public class WineMapper {
    public static WineEntity toEntity(Wine wine, Region region, Country country, Grape grape) {

        return WineEntity.WineEntityBuilder.aWineEntity()
                .withId(wine.getId())
                .withName(wine.getName())
                .withWinery(wine.getWinery())
                .withRate(wine.getRate())
                .withGrape(GrapeEntity.GrapeEntityBuilder.aGrapeEntity()
                            .withId(grape.getId())
                            .withName(grape.getName())
                            .build())
                .withRegion(RegionEntity.RegionEntityBuilder.aRegionEntity()
                            .withId(region.getId())
                            .withName(region.getName())
                            .withCountry(CountryEntity.CountryEntityBuilder.aCountryEntity()
                                        .withId(country.getId())
                                        .withName(country.getName())
                                        .build())
                            .build())
                .build();
    }


    public static Wine toDTO(WineEntity entity) {
        return Wine.WineBuilder.aWine()
                .withId(entity.getId())
                .withName(entity.getName())
                .withWinery(entity.getWinery())
                .withRate(entity.getRate())
                .withGrape(entity.getGrape().toString())
                .withRegion(entity.getRegion().toString())
                .withCountry(entity.getRegion().getCountry().toString())
                .build();
    }
}
