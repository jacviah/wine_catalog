package by.jacviah.winery.model;

import java.util.Objects;

public class Grape {
    Long id;
    String name;

    public Grape(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grape)) return false;
        Grape grape = (Grape) o;
        return Objects.equals(getName(), grape.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Grape{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
