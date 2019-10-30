package by.jacviah.winery.dao.entity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.Year;

public class BottleEntity {

    @ManyToOne
    @JoinColumn(name = "wine_id")
    private WineEntity wine;
    //User user;

    @Column(name = "year")
    private Year year;

    @Column(name = "status")
    private boolean isDrunk;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "rate")
    private String rate;

    public BottleEntity() {
    }
}
