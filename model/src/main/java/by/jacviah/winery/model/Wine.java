package by.jacviah.winery.model;

import java.io.Serializable;
import java.util.Objects;

public class Wine implements Serializable{
    Long id;
    String country;
    String region;
    String grape;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getGrape() {
        return grape;
    }

    public void setGrape(String grape) {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wine wine = (Wine) o;
        return Objects.equals(name, wine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Wine{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", grape='" + grape + '\'' +
                ", name='" + name + '\'' +
                ", winery='" + winery + '\'' +
                ", rate=" + rate +
                '}';
    }


    public static final class WineBuilder {
        Long id;
        String country;
        String region;
        String grape;
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

        public WineBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public WineBuilder withRegion(String region) {
            this.region = region;
            return this;
        }

        public WineBuilder withGrape(String grape) {
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
