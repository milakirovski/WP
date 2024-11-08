package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// In memory dataBase for all events
@Component
public class DataHolder {

    public static List<Event> events = new ArrayList<>();
    public static List<EventBooking> bookedEvents = new ArrayList<>();


    @PostConstruct
    public void init(){
        events.add(new Event("Birthday party","An event celebrating someones date of birth each year",9.67, new Location("The Fun Zone Arcade", "Orlando", "USA")));
        events.add(new Event("Graduation party","An event celebrating the completion of an academic program",10.0,new Location("Skyline Banquet Hall", "Chicago", "USA")));
        events.add(new Event("Wedding","A planned ceremonial event celebrating a marriage",7.88,new Location("Taj Mahal", "Agra", "India")));
        events.add(new Event("Themed Costume Party"," An event where attendees dress up according to a specific theme, such as a decade (e.g., the '80s), movies, or fictional characters.",3.26,new Location("Masquerade Ball Venue", "New Orleans", "USA")));
        events.add(new Event("Family Reunion","A gathering of family members, typically organized to reconnect, celebrate heritage, and enjoy shared activities.",10.00,new Location("Sunnybrook Park Pavilion", "Toronto", "Canada")));
        events.add(new Event("Music Concert or Festival","Live music events featuring various artists or bands, often held in larger venues or outdoor spaces",5.34, new Location("Sydney Opera House", "Sydney", "Australia")));
        events.add(new Event("Movie night","A relaxed event where people gather to watch one or more movies together",3.18,new Location("Grauman's Chinese Theatre", "Los Angeles", "USA")));
        events.add(new Event("Book Club Meeting","A gathering where members discuss a selected book, often accompanied by snacks or refreshments.",7.25,new Location("British Library", "London", "UK")));
        events.add(new Event("Charity Fundraiser","An event aimed at raising funds for a specific cause, often including activities like auctions, raffles, or donation drives.",9.89,new Location("The Red Cross Headquarters", "Geneva", "Switzerland")));
        events.add(new Event("Outdoor Picnic"," A casual gathering in a park or outdoor setting where attendees bring food and enjoy nature.",4.76, new Location("Central Park", "New York", "USA")));
    }
}
