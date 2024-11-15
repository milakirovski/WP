package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bookedEvent")
public class BookedEventController {

    @GetMapping
    public String getBookingConfirmationPage(@RequestParam String event,
                                             @RequestParam Integer numTickets,
                                             HttpServletRequest request,
                                             Model model){

        model.addAttribute("selectedEventName", event);
        model.addAttribute("numTicks", numTickets);
        model.addAttribute("ipAddress", request.getRemoteAddr());
        model.addAttribute("attendee", request.getHeader("host"));

        return "bookingConfirmation";
    }


}
