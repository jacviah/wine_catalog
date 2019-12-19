package by.jacviah.winery.web.rq;

import javax.validation.constraints.NotBlank;

public class CreateBottle {
    @NotBlank
    private String winery;
    @NotBlank
    private String wine;
    @NotBlank
    private String country;
    @NotBlank
    private String region;
    @NotBlank
    private String grape;
    @NotBlank
    private int year;

    public String getWinery() {
        return winery;
    }

    public void setWinery(String winery) {
        this.winery = winery;
    }

    public String getWine() {
        return wine;
    }

    public void setWine(String wine) {
        this.wine = wine;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
