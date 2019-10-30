package by.jacviah.winery.dao.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Year;

@Entity
@Table(name = "bottle")
public class BottleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

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
