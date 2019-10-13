package by.jacviah.winery.model;

import java.time.LocalDate;
import java.time.Year;

public class Bottle {
    Wine wine;
    User user;
    Year year;
    boolean isDrunk;
    LocalDate date;
    Rate rate;

    public Bottle() {
    }

    public Bottle(Wine wine, User user, Year year) {
        this.wine = wine;
        this.user = user;
        this.year = year;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bottle)) return false;

        Bottle bottle = (Bottle) o;

        if (getWine() != null ? !getWine().equals(bottle.getWine()) : bottle.getWine() != null) return false;
        return getYear() != null ? getYear().equals(bottle.getYear()) : bottle.getYear() == null;

    }

    @Override
    public int hashCode() {
        int result = getWine() != null ? getWine().hashCode() : 0;
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bottle{" +
                "wine=" + wine +
                ", user=" + user +
                ", year=" + year +
                ", isDrunk=" + isDrunk +
                ", date=" + date +
                ", rate=" + rate +
                '}';
    }
}
