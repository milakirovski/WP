package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookedEventRepository {

    public List<EventBooking> findAllBookedEvents(EventBooking eventBooking) {
        return DataHolder.bookedEvents;
    }

    public void deleteBookedEventByName(String name) {
        DataHolder.events.removeIf(bookedEv -> bookedEv.getName().equalsIgnoreCase(name));
    }

    public void addBookedEventToList(EventBooking eventBooking) {
        DataHolder.bookedEvents.add(eventBooking);
    }
}
