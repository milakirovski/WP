package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Location {
    private Long id;
    private String name;
    private String city;
    private String country;
    private String address;
    private String capacity;
    private String description;

    public Location(String name, String city, String country, String address, String capacity, String description) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.city = city;
        this.country = country;
        this.address = address;
        this.capacity = capacity;
        this.description = description;
    }

    @Override
    public String toString() {
        return name + " " + city + " " + country + " " + address + " " + capacity + " " + description;
    }
}
