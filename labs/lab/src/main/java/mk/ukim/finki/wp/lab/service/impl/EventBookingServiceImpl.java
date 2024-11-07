package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.repository.BookedEventRepository;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

@Service
public class EventBookingServiceImpl implements EventBookingService {

    private final BookedEventRepository bookedEventRepository;

    public EventBookingServiceImpl(BookedEventRepository bookedEventRepository) {
        this.bookedEventRepository = bookedEventRepository;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        EventBooking newEventBooking = new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);
        bookedEventRepository.addBookedEventToList(newEventBooking);
        return newEventBooking;
    }

}
