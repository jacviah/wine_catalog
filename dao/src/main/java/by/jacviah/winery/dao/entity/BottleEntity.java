package by.jacviah.winery.dao.entity;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@DynamicUpdate
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Table(name = "bottle")
public class BottleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wine_id")
    private WineEntity wine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "year")
    private String year;

    @Column(name = "status")
    private boolean isDrunk;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "rate")
    private Integer rate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WineEntity getWine() {
        return wine;
    }

    public void setWine(WineEntity wine) {
        this.wine = wine;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public boolean isDrunk() {
        return isDrunk;
    }

    public void setDrunk(boolean drunk) {
        isDrunk = drunk;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BottleEntity)) return false;
        BottleEntity that = (BottleEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getWine(), that.getWine()) &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getYear(), that.getYear());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getWine(), getUser(), getYear());
    }

    @Override
    public String toString() {
        return "BottleEntity{" +
                "wine=" + wine +
                ", year=" + year +
                ", isDrunk=" + isDrunk +
                ", date=" + date +
                ", rate='" + rate + '\'' +
                '}';
    }

    public static final class BottleEntityBuilder {
        private Long id;
        private WineEntity wine;
        private String year;
        private boolean isDrunk;
        private UserEntity user;
        private LocalDate date;
        private Integer rate;

        private BottleEntityBuilder() {
        }

        public static BottleEntityBuilder aBottleEntity() {
            return new BottleEntityBuilder();
        }

        public BottleEntityBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public BottleEntityBuilder withWine(WineEntity wine) {
            this.wine = wine;
            return this;
        }

        public BottleEntityBuilder withYear(String year) {
            this.year = year;
            return this;
        }

        public BottleEntityBuilder withIsDrunk(boolean isDrunk) {
            this.isDrunk = isDrunk;
            return this;
        }

        public BottleEntityBuilder withUser(UserEntity user) {
            this.user = user;
            return this;
        }
        public BottleEntityBuilder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public BottleEntityBuilder withRate(Integer rate) {
            this.rate = rate;
            return this;
        }

        public BottleEntity build() {
            BottleEntity bottleEntity = new BottleEntity();
            bottleEntity.date = this.date;
            bottleEntity.rate = this.rate;
            bottleEntity.id = this.id;
            bottleEntity.user = this.user;
            bottleEntity.year = this.year;
            bottleEntity.isDrunk = this.isDrunk;
            bottleEntity.wine = this.wine;
            return bottleEntity;
        }
    }
}
