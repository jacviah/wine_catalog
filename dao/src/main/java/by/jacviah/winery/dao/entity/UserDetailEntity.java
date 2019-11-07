package by.jacviah.winery.dao.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_detail")
public class UserDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public UserDetailEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDetailEntity)) return false;
        UserDetailEntity that = (UserDetailEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser());
    }

    @Override
    public String toString() {
        return "UserDetailEntity{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", user=" + user.getUsername() +
                '}';
    }

    public static final class UserDetailEntityBuilder {
        private Long id;
        private String description;
        private UserEntity user;

        private UserDetailEntityBuilder() {
        }

        public static UserDetailEntityBuilder anUserDetailEntity() {
            return new UserDetailEntityBuilder();
        }

        public UserDetailEntityBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserDetailEntityBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public UserDetailEntityBuilder withUser(UserEntity user) {
            this.user = user;
            return this;
        }

        public UserDetailEntity build() {
            UserDetailEntity userDetailEntity = new UserDetailEntity();
            userDetailEntity.setId(id);
            userDetailEntity.setDescription(description);
            userDetailEntity.setUser(user);
            return userDetailEntity;
        }
    }
}
