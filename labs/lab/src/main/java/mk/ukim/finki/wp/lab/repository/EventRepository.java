package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;

//CRUD operations
//For handling in-memory storage events
@Repository
public class EventRepository {

    public List<Event> findAll() {
        return DataHolder.events;
    }

    public Event createEvent(String name, String description, double score, Location location) {
        return new Event(name, description, score, location);
    }

    public void deleteEventByName(String name) {
        DataHolder.events.removeIf(event -> event.getName().equalsIgnoreCase(name));
    }

    public void addEvent(Event event) {
        DataHolder.events.add(event);
    }
}
