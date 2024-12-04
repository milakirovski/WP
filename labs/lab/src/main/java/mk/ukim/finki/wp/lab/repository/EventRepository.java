package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.Review;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    public Optional<Event> save(String name, String description, double popularityScore, Location location) {

        DataHolder.events.removeIf(ev -> ev.getName().equalsIgnoreCase(name));

        Event event = createEvent(name, description, popularityScore, location);
        DataHolder.events.add(event);
        return Optional.of(event);

    }

    public Optional<Event> findById(long id) {
        return DataHolder.events.stream().filter(event -> event.getId().equals(id)).findFirst();
    }

    public void deleteEventById(long id) {
        DataHolder.events.removeIf(event -> event.getId().equals(id));
    }

    // za dadeno id na event stavi mu review
    //TODO proveri posle vo logikata
    public void addReviewToEvent(Long eventId, Review review){
        if(findById(eventId).isPresent()){
            Event event = findById(eventId).get();
            event.getReviewList().add(review);
        }
    }

}
