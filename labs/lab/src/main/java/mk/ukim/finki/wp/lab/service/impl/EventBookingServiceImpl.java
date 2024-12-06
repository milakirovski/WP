package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryBookedEventRepository;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

@Service
public class EventBookingServiceImpl implements EventBookingService {

    private final InMemoryBookedEventRepository inMemoryBookedEventRepository;

    public EventBookingServiceImpl(InMemoryBookedEventRepository inMemoryBookedEventRepository) {
        this.inMemoryBookedEventRepository = inMemoryBookedEventRepository;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        EventBooking newEventBooking = new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);
        inMemoryBookedEventRepository.addBookedEventToList(newEventBooking);
        return newEventBooking;
    }

}
