package by.jacviah.winery.dao.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "grape")
public class GrapeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    Long id;

    @Column(name = "name", unique = true)
    String name;

    @OneToMany(mappedBy = "grape", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WineEntity> wines = new ArrayList<>();

    public GrapeEntity() {
    }

    public GrapeEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public GrapeEntity(Long id, String name, List<WineEntity> wines) {
        this.id = id;
        this.name = name;
        this.wines = wines;
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

    public List<WineEntity> getWines() {
        return wines;
    }

    public void setRegions(List<WineEntity> wines) {
        this.wines = wines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GrapeEntity)) return false;
        GrapeEntity that = (GrapeEntity) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
