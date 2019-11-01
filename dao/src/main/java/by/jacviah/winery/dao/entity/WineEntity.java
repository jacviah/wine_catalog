package by.jacviah.winery.dao.entity;

import by.jacviah.winery.model.Rate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "wine")
public class WineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private RegionEntity region;

    @ManyToOne
    @JoinColumn(name = "grape_id")
    private GrapeEntity grape;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "winery")
    private String winery;

    @Column(name = "avg_rate")
    private Double rate;

    @OneToMany(mappedBy = "wine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BottleEntity> bottle = new ArrayList<>();

    public WineEntity() {
    }

    public WineEntity (Long id, RegionEntity region, GrapeEntity grape, String name, String winery, Double rate, List<BottleEntity> bottle) {
        this.id = id;
        this.region = region;
        this.grape = grape;
        this.name = name;
        this.winery = winery;
        this.rate = rate;
        this.bottle = bottle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public List<BottleEntity> getBottle() {
        return bottle;
    }

    public void setBottle(List<BottleEntity> bottle) {
        this.bottle = bottle;
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

