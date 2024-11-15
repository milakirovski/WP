package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.exceptions.LocationNotFoundException;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(Model model,
                                @RequestParam(required = false) String searchName,
                                @RequestParam(required = false) Double searchRating,
                                @RequestParam(required = false) String locationName,
                                @RequestParam(required = false) String locationCity,
                                @RequestParam(required = false) String locationCountry) {

        model.addAttribute("events", eventService.listAll());

        // Handle event search filters
        handleEventSearchFilters(model, searchName, searchRating);

        // Handle location filters
        handleLocationFilters(model, locationName, locationCity, locationCountry);

        return "listEvents";
    }

    private void handleEventSearchFilters(Model model, String searchName, Double searchRating) {
        if (searchName != null && !searchName.isEmpty()) {
            List<Event> foundEventsByName = eventService.searchEventsByName(searchName);
            model.addAttribute("sn", searchName);
            model.addAttribute("searchEventsByName", foundEventsByName);
        }

        if (searchRating != null && searchRating > 0.0) {
            List<Event> foundEventsByRating = eventService.searchEventByRating(searchRating);
            model.addAttribute("sr", searchRating);
            model.addAttribute("searchEventsByRating", foundEventsByRating);
        }
    }

    private void handleLocationFilters(Model model, String locationName, String locationCity, String locationCountry) {
        if (locationName != null && !locationName.isEmpty()) {
            List<Event> filteredByName = eventService.filterEventsByLocationName(locationName);
            model.addAttribute("ln", locationName);
            model.addAttribute("locationsFilteredByName", filteredByName);
        }

        if (locationCity != null && !locationCity.isEmpty()) {
            List<Event> filteredByCity = eventService.filterEventsByLocationCity(locationCity);
            model.addAttribute("lc", locationCity);
            model.addAttribute("locationsFilteredByCity", filteredByCity);
        }

        if (locationCountry != null && !locationCountry.isEmpty()) {
            List<Event> filteredByCountry = eventService.filterEventsByLocationCountry(locationCountry);
            model.addAttribute("lcountry", locationCountry);
            model.addAttribute("locationsFilteredByCountry", filteredByCountry);
        }
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Double popularityScore,
                            @RequestParam Long location) throws LocationNotFoundException {
        eventService.save(name, description, popularityScore, location);
        return "redirect:/events";

    }

    //show the page where we add the events data -> (Add an Event button)
    @GetMapping("/add-event")
    public String getEventAdditionOrEditPage(Model model) {

        List<Location> locations = locationService.findAll();
        model.addAttribute("locations", locations);

        return "add-event";
    }

    //show modified version of the add-event html, for modification an event ifo
    @GetMapping("/edit/{eventId}")
    public String editEvent(@PathVariable Long eventId,
                            Model model) {
        if (eventService.findById(eventId).isPresent()) {
            Event event = eventService.findById(eventId).get();

            List<Location> locations = locationService.findAll();

            model.addAttribute("locations", locations);
            model.addAttribute("event", event);

            return "add-event";
        } else {
            return "redirect:/events?error=EventNotFound";
        }
    }

    @GetMapping("/delete/{eventId}")
    public String deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEventById(eventId);
        return "redirect:/events";
    }

}
