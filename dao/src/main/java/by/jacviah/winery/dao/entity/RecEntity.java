package by.jacviah.winery.dao.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "recommendation")
public class RecEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity sommelier;

    @Column(name = "description")
    String message;

    @ManyToMany(mappedBy = "recommendations")
    @JoinTable(name = "wine_recommendation",joinColumns = {@JoinColumn(name = "rec_id")},
            inverseJoinColumns = {@JoinColumn(name = "wine_id")})
    Set<WineEntity> wines = new HashSet<>();

    public RecEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getSommelier() {
        return sommelier;
    }

    public void setSommelier(UserEntity sommelier) {
        this.sommelier = sommelier;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public  Set<WineEntity> getWines() {
        return wines;
    }

    public void setWines( Set<WineEntity> wines) {
        this.wines = wines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecEntity)) return false;
        RecEntity that = (RecEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getSommelier(), that.getSommelier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSommelier());
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "id=" + id +
                ", sommelier=" + sommelier.getUsername() +
                ", message='" + message +
                '}';
    }

    public static final class RecEntityBuilder {
        Long id;
        UserEntity sommelier;
        String message;
        Set<WineEntity> wines;

        private RecEntityBuilder() {
        }

        public static RecEntityBuilder aRecEntity() {
            return new RecEntityBuilder();
        }

        public RecEntityBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public RecEntityBuilder withSommelier(UserEntity sommelier) {
            this.sommelier = sommelier;
            return this;
        }

        public RecEntityBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public RecEntityBuilder withWines( Set<WineEntity> wines) {
            this.wines = wines;
            return this;
        }

        public RecEntity build() {
            RecEntity recEntity = new RecEntity();
            recEntity.setId(id);
            recEntity.setSommelier(sommelier);
            recEntity.setMessage(message);
            recEntity.setWines(wines);
            return recEntity;
        }
    }
}
