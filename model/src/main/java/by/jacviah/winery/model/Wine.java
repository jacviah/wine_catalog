package by.jacviah.winery.model;

import java.io.Serializable;
import java.util.Objects;

public class Wine implements Serializable{
    Long id;
    Country country;
    Region region;
    Grape grape;
    String name;
    String winery;
    Double rate;

    public Wine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Grape getGrape() {
        return grape;
    }

    public void setGrape(Grape grape) {
        this.grape = grape;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWinery() {
        return winery;
    }

    public void setWinery(String winery) {
        this.winery = winery;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wine)) return false;
        Wine wine = (Wine) o;
        return Objects.equals(getId(), wine.getId()) &&
                Objects.equals(getCountry(), wine.getCountry()) &&
                Objects.equals(getRegion(), wine.getRegion()) &&
                Objects.equals(getGrape(), wine.getGrape()) &&
                Objects.equals(getName(), wine.getName()) &&
                Objects.equals(getWinery(), wine.getWinery());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCountry(), getRegion(), getGrape(), getName(), getWinery());
    }

    @Override
    public String toString() {
        return "Wine{" +
                "id=" + id +
                ", country=" + country.getName() +
                ", region=" + region.getName() +
                ", grape=" + grape.getName() +
                ", name='" + name + '\'' +
                ", winery='" + winery + '\'' +
                ", rate=" + rate +
                '}';
    }


    public static final class WineBuilder {
        Long id;
        Country country;
        Region region;
        Grape grape;
        String name;
        String winery;
        Double rate;

        private WineBuilder() {
        }

        public static WineBuilder aWine() {
            return new WineBuilder();
        }

        public WineBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public WineBuilder withCountry(Country country) {
            this.country = country;
            return this;
        }

        public WineBuilder withRegion(Region region) {
            this.region = region;
            return this;
        }

        public WineBuilder withGrape(Grape grape) {
            this.grape = grape;
            return this;
        }

        public WineBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public WineBuilder withWinery(String winery) {
            this.winery = winery;
            return this;
        }

        public WineBuilder withRate(Double rate) {
            this.rate = rate;
            return this;
        }

        public Wine build() {
            Wine wine = new Wine();
            wine.setId(id);
            wine.setCountry(country);
            wine.setRegion(region);
            wine.setGrape(grape);
            wine.setName(name);
            wine.setWinery(winery);
            wine.setRate(rate);
            return wine;
        }
    }
}
