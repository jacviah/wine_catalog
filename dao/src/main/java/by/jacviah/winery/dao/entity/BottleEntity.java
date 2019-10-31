package by.jacviah.winery.dao.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Year;

@Entity
@Table(name = "bottle")
public class BottleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private final Long id;

    @ManyToOne
    @JoinColumn(name = "wine_id")
    private final WineEntity wine;
    //User user;

    @Column(name = "year")
    private final Year year;

    @Column(name = "status")
    private final boolean isDrunk;

    @Column(name = "date")
    private final LocalDate date;

    @Column(name = "rate")
    private final String rate;

    public BottleEntity(Builder builder) {
        this.id = builder.id;
        this.wine = builder.wine;
        this.year = builder.year;
        this.isDrunk = builder.isDrunk;
        this.date = builder.date;
        this.rate = builder.rate;
    }

    public static class Builder {
        private Long id;
        private WineEntity wine;
        //User user;
        private Year year;
        private boolean isDrunk;
        private LocalDate date;
        private String rate;


        public static Builder newInstance() {
            return new Builder();
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setWine(WineEntity wine) {
            this.wine = wine;
            return this;
        }

        public Builder setYear(Year year) {
            this.year = year;
            return this;
        }
        public Builder setStatus(boolean isDrunk) {
            this.id = id;
            return this;
        }

        public Builder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder setRate(String rate) {
            this.rate = rate;
            return this;
        }

        public BottleEntity build() {
            return new BottleEntity(this);
        }
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
}
