package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Review {
    private Long id;
    private String content;
    private double stars;

    public Review(String content, double stars) {
        this.id = (long) (Math.random() * 1000);
        this.content = content;
        this.stars = stars;
    }
}
