package by.jacviah.winery.web.rq;

import by.jacviah.winery.model.Recommendation;
import by.jacviah.winery.model.Wine;

import java.util.List;
import java.util.stream.Collectors;

public class RecommendForm {
    String description;
    List<WineForm> wines;

    public RecommendForm(Recommendation recommendation) {
        this.description = recommendation.getMessage();
        this.wines = recommendation.getWines().stream()
                .map(wine -> new WineForm(wine)).collect(Collectors.toList());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<WineForm> getWines() {
        return wines;
    }

    public void setWines(List<WineForm> wines) {
        this.wines = wines;
    }
}
