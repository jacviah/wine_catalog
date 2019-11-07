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
    public static WineEntity toEntity(Wine wine) {
        return WineEntity.WineEntityBuilder.aWineEntity()
                .withId(wine.getId())
                .withName(wine.getName())
                .withWinery(wine.getWinery())
                .withRate(wine.getRate())
                .withGrape(GrapeEntity.GrapeEntityBuilder.aGrapeEntity()
                            .withId(wine.getGrape().getId())
                            .withName(wine.getGrape().getName())
                            .build())
                .withRegion(RegionEntity.RegionEntityBuilder.aRegionEntity()
                            .withId(wine.getRegion().getId())
                            .withName(wine.getRegion().getName())
                            .withCountry(CountryEntity.CountryEntityBuilder.aCountryEntity()
                                        .withId(wine.getCountry().getId())
                                        .withName(wine.getCountry().getName())
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
                .withGrape(new Grape(
                        entity.getGrape().getId(),
                        entity.getGrape().getName()))
                .withRegion(new Region(
                        entity.getRegion().getId(),
                        entity.getRegion().getName()))
                .withCountry(new Country(
                        entity.getRegion().getCountry().getId(),
                        entity.getRegion().getCountry().getName()))
                .build();
    }
}
