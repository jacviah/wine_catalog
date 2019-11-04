package by.jacviah.winery.dao.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bottle")
public class BottleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wine_id")
    private WineEntity wine;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "year")
    private String year;

    @Column(name = "status")
    private boolean isDrunk;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "rate")
    private int rate;


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
        private int rate;

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

        public BottleEntityBuilder withRate(int rate) {
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
