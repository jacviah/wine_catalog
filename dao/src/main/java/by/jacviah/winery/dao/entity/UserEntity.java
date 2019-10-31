package by.jacviah.winery.dao.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
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

    public static final class UserEntityBuilder {

        private Long id;
        private String username;
        private String password;
        private String uuid;
        private String role;

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

        public UserEntity build() {
            UserEntity userEntity = new UserEntity();
            userEntity.role = this.role;
            userEntity.password = this.password;
            userEntity.username = this.username;
            userEntity.id = this.id;
            userEntity.uuid = this.uuid;
            return userEntity;
        }
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
}
