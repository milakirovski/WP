package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.Review;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// In memory dataBase for all events
@Component
public class DataHolder {

    public static List<Event> events = new ArrayList<>();
    public static List<EventBooking> bookedEvents = new ArrayList<>();
    public static List<Location> locations = new ArrayList<>();

    @PostConstruct
    public void init(){

        Location eiffel_tower = new Location(
                "Eiffel Tower",
                "Paris",
                "France",
                "Champ de Mars, 5 Avenue Anatole, 75007 Paris, France",
                "100",
                "An iconic landmark of France and one of the most recognizable structures in the world."
        );
        locations.add(eiffel_tower);

        Location central_park = new Location(
                "Central Park",
                "New York City",
                "USA",
                "Liberty Island, New York, NY 10004, United States",
                "5000",
                "Central Park, New York, NY 10024, United States"
        );
        locations.add(central_park);

        Location tokyo_tower = new Location(
                "Tokyo Tower",
                "Tokyo",
                "Japan",
                "4 Chome-2-8 Shibakoen, Minato City, Tokyo 105-0011, Japan",
                "300",
                "A communications and observation tower in Tokyo inspired by the Eiffel Tower."
        );
        locations.add(tokyo_tower);

        Location opera_house = new Location(
                "Sydney Opera House",
                "Sydney",
                "Australia",
                "Bennelong Point, Sydney NSW 2000, Australia",
                "400",
                "An architectural marvel, the Sydney Opera House is a UNESCO World Heritage site."
        );
        locations.add(opera_house);

        Location lakeview_Park = new Location(
                "Lakeview Park",
                "Austin",
                "USA",
                "1600 S Lakeshore Blvd, Austin, TX 78741, United States",
                "1100",
                "A spacious park with picnic areas, scenic lake views, and recreational facilities, perfect for family gatherings and reunions."
        );
        locations.add(lakeview_Park);


        Location Christ_the_Redeemer = new Location(
                "Christ the Redeemer",
                "Rio de Janeiro",
                "Brazil",
                "Parque Nacional da Tijuca - Alto da Boa Vista, Rio de Janeiro - RJ, Brazil",
                "600",
                "A massive statue of Jesus Christ overlooking the city, one of the New Seven Wonders of the World."
        );
        locations.add(Christ_the_Redeemer);

        Location library = new Location(
                "New York Public Library - Stephen A. Schwarzman Building",
                "New York City",
                "USA",
                "476 5th Ave, New York, NY 10018, United States",
                "900",
                "One of the largest public libraries in the world, renowned for its beautiful architecture and extensive collection of books and resources."
        );
        locations.add(library);

        Location cinema = new Location(
                "AMC Empire 25",
                "New York City",
                "USA",
                "234 W 42nd St, New York, NY 10036, United States",
                "800",
                "A popular cinema located in the heart of Times Square, featuring multiple screens and the latest movie releases."
        );
        locations.add(cinema);

        Location taj_mahal = new Location(
                "Taj Mahal",
                "Agra",
                "India",
                "Dharmapuri, Forest Colony, Tajganj, Agra, Uttar Pradesh 282001, India",
                "900",
                "A stunning white marble mausoleum, widely recognized as a symbol of love and devotion."
        );
        locations.add(taj_mahal);

        Location red_cross = new Location(
                "American Red Cross - National Headquarters",
                "Washington, D.C.",
                "USA",
                "430 17th St NW, Washington, D.C. 20006, United States",
                "1000",
                "The headquarters of the American Red Cross, an organization providing emergency assistance, disaster relief, and education."
        );
        locations.add(red_cross);


        events.add(new Event("Birthday party","An event celebrating someones date of birth each year",9.67, eiffel_tower));
        events.add(new Event("Graduation party","An event celebrating the completion of an academic program",10.0,tokyo_tower));
        events.add(new Event("Wedding","A planned ceremonial event celebrating a marriage",7.88, taj_mahal));
        events.add(new Event("Themed Costume Party"," An event where attendees dress up according to a specific theme, such as a decade (e.g., the '80s), movies, or fictional characters.",3.26, Christ_the_Redeemer));
        events.add(new Event("Family Reunion","A gathering of family members, typically organized to reconnect, celebrate heritage, and enjoy shared activities.",10.00,lakeview_Park));
        events.add(new Event("Music Concert or Festival","Live music events featuring various artists or bands, often held in larger venues or outdoor spaces",5.34, opera_house));
        events.add(new Event("Movie night","A relaxed event where people gather to watch one or more movies together",3.18,cinema));
        events.add(new Event("Book Club Meeting","A gathering where members discuss a selected book, often accompanied by snacks or refreshments.",7.25,library));
        events.add(new Event("Charity Fundraiser","An event aimed at raising funds for a specific cause, often including activities like auctions, raffles, or donation drives.",9.89,red_cross));
        events.add(new Event("Outdoor Picnic"," A casual gathering in a park or outdoor setting where attendees bring food and enjoy nature.",4.76, central_park));
    }
}
