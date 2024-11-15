package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.exceptions.LocationNotFoundException;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);

    List<Event> searchEventsByName(String text);
    List<Event> searchEventByRating(Double rating);

    List<Event> filterEventsByLocationName(String locationName);
    List<Event> filterEventsByLocationCity(String locationCity);
    List<Event> filterEventsByLocationCountry(String locationCountry);

    Optional<Event> save(String name, String description, double popularityScore, Long locationId) throws LocationNotFoundException;

    public Optional<Event> findById(long id);

    public void deleteEventById(long id);
}
