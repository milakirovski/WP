package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String content;
    private double stars;
    @ManyToOne
    private Event event;

    public Review(String content, double stars) {
        this.content = content;
        this.stars = stars;
    }
}
