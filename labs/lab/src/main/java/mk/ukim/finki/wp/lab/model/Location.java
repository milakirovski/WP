package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
    private String name;
    private String city;
    private String country;

    @Override
    public String toString() {
        return name + " " + city + " " + country;
    }
}
