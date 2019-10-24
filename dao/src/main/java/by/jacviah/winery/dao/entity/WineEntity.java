package by.jacviah.winery.dao.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "wine")
public class WineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private RegionEntity region;

    @ManyToOne
    @JoinColumn(name = "grape_id")
    private GrapeEntity grape;

    @Column(name = "name", unique = true)
    String name;

    @Column(name = "winery")
    String winery;

    @Column(name = "rate")
    private int rate;

    public WineEntity() {
    }

    public WineEntity (int id, CountryEntity country, RegionEntity region, GrapeEntity grape, String name, String winery, int rate) {
        this.id = id;
        this.country = country;
        this.region = region;
        this.grape = grape;
        this.name = name;
        this.winery = winery;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RegionEntity getRegion() {
        return region;
    }

    public void setRegion(RegionEntity region) {
        this.region = region;
    }

    public GrapeEntity getGrape() {
        return grape;
    }

    public void setGrape(GrapeEntity grape) {
        this.grape = grape;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWinery() {
        return winery;
    }

    public void setWinery(String winery) {
        this.winery = winery;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WineEntity wine = (WineEntity) o;
        return Objects.equals(name, wine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

