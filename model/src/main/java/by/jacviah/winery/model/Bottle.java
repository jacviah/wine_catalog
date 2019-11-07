package by.jacviah.winery.model;

import java.time.LocalDate;
import java.time.Year;
import java.util.Objects;

public class Bottle {
    Long id;
    Wine wine;
    User user;
    Year year;
    boolean isDrunk;
    LocalDate date;
    Rate rate;

    public Bottle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Wine getWine() {
        return wine;
    }

    public void setWine(Wine wine) {
        this.wine = wine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
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

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bottle)) return false;
        Bottle bottle = (Bottle) o;
        return Objects.equals(getId(), bottle.getId()) &&
                Objects.equals(getWine(), bottle.getWine()) &&
                Objects.equals(getUser(), bottle.getUser()) &&
                Objects.equals(getYear(), bottle.getYear());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getWine(), getUser(), getYear());
    }

    @Override
    public String toString() {
        return "Bottle{" +
                "wine=" + wine.getName() + " " + wine.getWinery() +
                ", user=" + user.getUsername() +
                ", year=" + year +
                ", isDrunk=" + isDrunk +
                ", date=" + date +
                ", rate=" + rate +
                '}';
    }


    public static final class BottleBuilder {
        Long id;
        Wine wine;
        User user;
        Year year;
        boolean isDrunk;
        LocalDate date;
        Rate rate;

        private BottleBuilder() {
        }

        public static BottleBuilder aBottle() {
            return new BottleBuilder();
        }

        public BottleBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public BottleBuilder withWine(Wine wine) {
            this.wine = wine;
            return this;
        }

        public BottleBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public BottleBuilder withYear(Year year) {
            this.year = year;
            return this;
        }

        public BottleBuilder withIsDrunk(boolean isDrunk) {
            this.isDrunk = isDrunk;
            return this;
        }

        public BottleBuilder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public BottleBuilder withRate(Rate rate) {
            this.rate = rate;
            return this;
        }

        public Bottle build() {
            Bottle bottle = new Bottle();
            bottle.setId(id);
            bottle.setWine(wine);
            bottle.setUser(user);
            bottle.setYear(year);
            bottle.setDate(date);
            bottle.setRate(rate);
            bottle.isDrunk = this.isDrunk;
            return bottle;
        }
    }
}
