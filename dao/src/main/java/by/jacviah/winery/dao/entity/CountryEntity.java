package by.jacviah.winery.dao.entity;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@DynamicUpdate
@OptimisticLocking(type = OptimisticLockType.ALL)
@Table(name = "country")
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    Long id;

    @Column(name = "name", unique = true)
    String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegionEntity> regions = new ArrayList<>();

    public CountryEntity() {
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

    public List<RegionEntity> getRegions() {
        return regions;
    }

    public void setRegions(List<RegionEntity> regions) {
        this.regions= regions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountryEntity)) return false;
        CountryEntity that = (CountryEntity) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "CountryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    public static final class CountryEntityBuilder {
        Long id;
        String name;
        private List<RegionEntity> regions = new ArrayList<>();

        private CountryEntityBuilder() {
        }

        public static CountryEntityBuilder aCountryEntity() {
            return new CountryEntityBuilder();
        }

        public CountryEntityBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CountryEntityBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CountryEntityBuilder withRegions(List<RegionEntity> regions) {
            this.regions = regions;
            return this;
        }

        public CountryEntity build() {
            CountryEntity countryEntity = new CountryEntity();
            countryEntity.setId(id);
            countryEntity.setName(name);
            countryEntity.setRegions(regions);
            return countryEntity;
        }
    }
}
