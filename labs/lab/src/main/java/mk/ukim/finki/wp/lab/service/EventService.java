package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Event;

import java.util.List;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);

    List<Event> searchEventsByName(String text);
    List<Event> searchEventByRating(double rating);
}
