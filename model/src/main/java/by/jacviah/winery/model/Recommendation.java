package by.jacviah.winery.model;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Recommendation {
    Long id;
    User sommelier;
    String message;
    Set<Wine> wines;

    public Recommendation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSommelier() {
        return sommelier;
    }

    public void setSommelier(User sommelier) {
        this.sommelier = sommelier;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<Wine> getWines() {
        return wines;
    }

    public void setWines(Set<Wine> wines) {
        this.wines = wines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recommendation)) return false;
        Recommendation that = (Recommendation) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getSommelier(), that.getSommelier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSommelier());
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "id=" + id +
                ", sommelier=" + sommelier.getUsername() +
                ", message='" + message + '\'' +
                ", wines=" + wines +
                '}';
    }


    public static final class RecommendationBuilder {
        Long id;
        User sommelier;
        String message;
        Set<Wine> wines;

        private RecommendationBuilder() {
        }

        public static RecommendationBuilder aRecommendation() {
            return new RecommendationBuilder();
        }

        public RecommendationBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public RecommendationBuilder withSommelier(User sommelier) {
            this.sommelier = sommelier;
            return this;
        }

        public RecommendationBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public RecommendationBuilder withWines(Set<Wine> wines) {
            this.wines = wines;
            return this;
        }

        public Recommendation build() {
            Recommendation recommendation = new Recommendation();
            recommendation.setId(id);
            recommendation.setSommelier(sommelier);
            recommendation.setMessage(message);
            recommendation.setWines(wines);
            return recommendation;
        }
    }
}
