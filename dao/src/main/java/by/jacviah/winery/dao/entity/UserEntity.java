package by.jacviah.winery.dao.entity;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import java.util.*;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicUpdate
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@Table(name = "user")
@SecondaryTable(name = "auth_user", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id"))
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "login")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "uuid", table = "auth_user")
    private String uuid;

    @Column(name = "role")
    private String role;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="sommelier_id")
    private UserEntity sommelier;

    @OneToMany(mappedBy="sommelier", fetch = FetchType.LAZY)
    private Set<UserEntity> subscribers = new HashSet<UserEntity>();

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private UserDetailEntity detail;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RecommendEntity> fromUser = new ArrayList<>();

    @OneToMany(mappedBy = "subscriber", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RecommendEntity> toUser= new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserDetailEntity getDetail() {
        return detail;
    }

    public void setDetail(UserDetailEntity detail) {
        this.detail = detail;
    }

    public List<RecommendEntity> getRecommendationFromUser() {
        return fromUser;
    }

    public void setRecommendationsFromUser(List<RecommendEntity> recommendationsFromUser) {
        this.fromUser = recommendationsFromUser;
    }

    public List<RecommendEntity> getRecommendationToUser() {
        return toUser;
    }

    public void setRecommendationsToUser(List<RecommendEntity> recommendationsToUser) {
        this.toUser = recommendationsToUser;
    }

    public UserEntity getSommelier() {
        return sommelier;
    }

    public void setSommelier(UserEntity sommelier) {
        this.sommelier = sommelier;
    }

    public Set<UserEntity> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<UserEntity> subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", uuid='" + uuid + '\'' +
                ", role='" + role + '\'' +
                '}';
    }


    public static final class UserEntityBuilder {
        private Long id;
        private String username;
        private String password;
        private String uuid;
        private String role;
        private UserEntity sommelier;
        private Set<UserEntity> subscribers = new HashSet<>();
        private UserDetailEntity detail;
        private List<RecommendEntity> fromUser = new ArrayList<>();
        private List<RecommendEntity> toUser = new ArrayList<>();

        private UserEntityBuilder() {
        }

        public static UserEntityBuilder anUserEntity() {
            return new UserEntityBuilder();
        }

        public UserEntityBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserEntityBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public UserEntityBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserEntityBuilder withUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public UserEntityBuilder withRole(String role) {
            this.role = role;
            return this;
        }

        public UserEntityBuilder withSommelier(UserEntity sommelier) {
            this.sommelier = sommelier;
            return this;
        }

        public UserEntityBuilder withSubscribers(Set<UserEntity> subscribers) {
            this.subscribers = subscribers;
            return this;
        }

        public UserEntityBuilder withDetail(UserDetailEntity detail) {
            this.detail = detail;
            return this;
        }

        public UserEntityBuilder withRecommendationsFromUser(List<RecommendEntity> fromUser) {
            this.fromUser = fromUser;
            return this;
        }

        public UserEntityBuilder withRecommendationsToUser(List<RecommendEntity> toUser) {
            this.toUser = toUser;
            return this;
        }

        public UserEntity build() {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(id);
            userEntity.setUsername(username);
            userEntity.setPassword(password);
            userEntity.setUuid(uuid);
            userEntity.setSommelier(sommelier);
            userEntity.setRole(role);
            userEntity.setDetail(detail);
            userEntity.setRecommendationsFromUser(fromUser);
            userEntity.setRecommendationsToUser(toUser);
            return userEntity;
        }
    }
}
