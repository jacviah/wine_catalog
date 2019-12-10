package by.jacviah.winery.dao.entity;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.util.*;

@Entity
@DynamicUpdate
@OptimisticLocking(type = OptimisticLockType.DIRTY)
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

    @ManyToMany(mappedBy = "wines")
     private Set<RecommendEntity> recommendations = new HashSet<>();;

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
        if (!(o instanceof WineEntity)) return false;
        WineEntity that = (WineEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getRegion(), that.getRegion()) &&
                Objects.equals(getGrape(), that.getGrape()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getWinery(), that.getWinery());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRegion(), getGrape(), getName(), getWinery());
    }

    @Override
    public String toString() {
        return "WineEntity{" +
                "id=" + id +
                ", region=" + region.getName() +
                ", grape=" + grape.getName() +
                ", name='" + name + '\'' +
                ", winery='" + winery + '\'' +
                ", rate=" + rate +
                ", bottle=" + //bottle +
                '}';
    }

    public static final class WineEntityBuilder {
        private Long id;
        private RegionEntity region;
        private GrapeEntity grape;
        private String name;
        private String winery;
        private Double rate;
        private List<BottleEntity> bottle = new ArrayList<>();

        private WineEntityBuilder() {
        }

        public static WineEntityBuilder aWineEntity() {
            return new WineEntityBuilder();
        }

        public WineEntityBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public WineEntityBuilder withRegion(RegionEntity region) {
            this.region = region;
            return this;
        }

        public WineEntityBuilder withGrape(GrapeEntity grape) {
            this.grape = grape;
            return this;
        }

        public WineEntityBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public WineEntityBuilder withWinery(String winery) {
            this.winery = winery;
            return this;
        }

        public WineEntityBuilder withRate(Double rate) {
            this.rate = rate;
            return this;
        }

        public WineEntityBuilder withBottle(List<BottleEntity> bottle) {
            this.bottle = bottle;
            return this;
        }

        public WineEntity build() {
            WineEntity wineEntity = new WineEntity();
            wineEntity.setId(id);
            wineEntity.setRegion(region);
            wineEntity.setGrape(grape);
            wineEntity.setName(name);
            wineEntity.setWinery(winery);
            wineEntity.setRate(rate);
            wineEntity.setBottle(bottle);
            return wineEntity;
        }
    }
}

