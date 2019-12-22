package by.jacviah.winery.web.rq;

import by.jacviah.winery.model.Wine;

public class WineForm {
    Long id;
    String name;
    String winery;
    String grape;
    String region;

    public WineForm(Wine wine) {
        this.id = wine.getId();
        this.name = wine.getName();
        this.winery = wine.getWinery();
        this.grape = wine.getGrape().getName();
        this.region = wine.getRegion().getName();
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

    public String getGrape() {
        return grape;
    }

    public void setGrape(String grape) {
        this.grape = grape;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
