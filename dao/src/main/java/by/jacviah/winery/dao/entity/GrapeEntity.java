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
    public String toString() {
        return "GrapeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", wines=" + wines +
                '}';
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


    public static final class GrapeEntityBuilder {
        Long id;
        String name;
        private List<WineEntity> wines = new ArrayList<>();

        private GrapeEntityBuilder() {
        }

        public static GrapeEntityBuilder aGrapeEntity() {
            return new GrapeEntityBuilder();
        }

        public GrapeEntityBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public GrapeEntityBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public GrapeEntityBuilder withWines(List<WineEntity> wines) {
            this.wines = wines;
            return this;
        }

        public GrapeEntity build() {
            GrapeEntity grapeEntity = new GrapeEntity();
            grapeEntity.setId(id);
            grapeEntity.setName(name);
            grapeEntity.wines = this.wines;
            return grapeEntity;
        }
    }
}
