package by.jacviah.winery.dao.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
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

    public CountryEntity(String name) {
        this.name = name;
    }

    public CountryEntity(Long id, String name, List<RegionEntity> regions) {
        this.id = id;
        this.name = name;
        this.regions = regions;
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
}
