package by.jacviah.winery.dao.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicUpdate
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@Table(name = "recommendation")
public class RecommendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    Long id;

    @ManyToOne
    @JoinColumn(name = "sommelier_id")
    UserEntity author;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity subscriber;

    @Column(name = "description")
    String message;

    @ManyToMany
    @JoinTable(name = "wine_recommendation",
                joinColumns = {@JoinColumn(name = "rec_id")},
                inverseJoinColumns = {@JoinColumn(name = "wine_id")})
    Set<WineEntity> wines = new HashSet<>();

    public RecommendEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity sommelier) {
        this.author = sommelier;
    }

    public UserEntity getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(UserEntity subscriber) {
        this.subscriber = subscriber;
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
        if (!(o instanceof RecommendEntity)) return false;
        RecommendEntity that = (RecommendEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getAuthor(), that.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor());
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "id=" + id +
                ", sommelier=" + author.getUsername() +
                ", message='" + message +
                '}';
    }

    public static final class RecEntityBuilder {
        Long id;
        UserEntity author;
        UserEntity subscriber;
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

        public RecEntityBuilder withAuthor(UserEntity sommelier) {
            this.author = sommelier;
            return this;
        }

        public RecEntityBuilder witSubscriber(UserEntity subscriber) {
            this.subscriber = subscriber;
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

        public RecommendEntity build() {
            RecommendEntity recEntity = new RecommendEntity();
            recEntity.setId(id);
            recEntity.setAuthor(author);
            recEntity.setSubscriber(subscriber);
            recEntity.setMessage(message);
            recEntity.setWines(wines);
            return recEntity;
        }
    }
}
