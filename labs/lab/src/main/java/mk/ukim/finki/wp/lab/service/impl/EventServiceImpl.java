package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.Review;
import mk.ukim.finki.wp.lab.model.exceptions.LocationNotFoundException;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryEventRepository;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryLocationRepository;
import mk.ukim.finki.wp.lab.repository.jpa.EventRepository;
import mk.ukim.finki.wp.lab.repository.jpa.LocationRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ReviewRepository;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final ReviewRepository reviewRepository;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository, ReviewRepository reviewRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.findAll().stream()
                .filter(event -> event.getName().toLowerCase().contains(text.toLowerCase()) || event.getDescription().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> searchEventsByName(String text) {
        return eventRepository.findAll().stream()
                .filter(event -> event.getName().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> searchEventByRating(Double rating) {
        return eventRepository.findAll().stream()
                .filter(event -> event.getPopularityScore() >= rating)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> filterEventsByLocationName(String locationName) {
        return eventRepository.findAll().stream()
                .filter(event -> event.getLocation().getName().toLowerCase().contains(locationName.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> filterEventsByLocationCity(String locationCity) {
        return eventRepository.findAll().stream()
                .filter(event -> event.getLocation().getCity().toLowerCase().contains(locationCity.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> filterEventsByLocationCountry(String locationCountry) {
        return  eventRepository.findAll().stream()
                .filter(event -> event.getLocation().getCountry().toLowerCase().contains(locationCountry.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Event> saveEvent(String name, String description, double popularityScore, Long locationId) throws LocationNotFoundException {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));
        return Optional.of(eventRepository.save(new Event(name, description, popularityScore, location)));
    }

    @Override
    public Optional<Event> findById(long id) {
        return eventRepository.findById(id);
    }

    @Override
    public void deleteEventById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public void addReviewToEvent(Long eventId, Review review) {
        eventRepository.findById(eventId).ifPresent(e -> {
            review.setEvent(e);
            reviewRepository.save(review);
        });
    }

}
