package by.jacviah.winery.web.rq;

import by.jacviah.winery.model.Bottle;

public class BottleForm {
    private String wine;
    private String winery;
    private String country;
    private String region;
    private String grape;
    private String year;
/*    private String rate;
    private String date;
    private String isDrunk;*/

    public BottleForm() {
    }


    public BottleForm(Bottle dto) {
        this.wine = dto.getWine().getName();
        this.winery = dto.getWine().getWinery();
        this.country = dto.getWine().getCountry().getName();
        this.region = dto.getWine().getRegion().getName();
        this.grape = dto.getWine().getGrape().getName();
        this.year = dto.getYear().toString();
    }

    public String getWine() {
        return wine;
    }

    public void setWine(String wine) {
        this.wine = wine;
    }

    public String getWinery() {
        return winery;
    }

    public void setWinery(String winery) {
        this.winery = winery;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

}
