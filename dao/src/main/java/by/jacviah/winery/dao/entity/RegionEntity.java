package by.jacviah.winery.dao.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "region")
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    Long id;

    @Column(name = "name", unique = true)
    String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WineEntity> wines = new ArrayList<>();

    public RegionEntity() {
    }

    public RegionEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RegionEntity(Long id, String name, CountryEntity country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof RegionEntity)) return false;
        RegionEntity that = (RegionEntity) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "RegionEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country.getName() +
                '}';
    }


    public static final class RegionEntityBuilder {
        Long id;
        String name;
        private CountryEntity country;

        private RegionEntityBuilder() {
        }

        public static RegionEntityBuilder aRegionEntity() {
            return new RegionEntityBuilder();
        }

        public RegionEntityBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public RegionEntityBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public RegionEntityBuilder withCountry(CountryEntity country) {
            this.country = country;
            return this;
        }

        public RegionEntity build() {
            RegionEntity regionEntity = new RegionEntity();
            regionEntity.setId(id);
            regionEntity.setName(name);
            regionEntity.setCountry(country);
            return regionEntity;
        }
    }
}
