package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "EventBookingServlet", urlPatterns = {"/event-booking"})
public class EventBookingServlet extends HttpServlet {

    private final EventBookingService eventBookingService;
    private final SpringTemplateEngine templateEngine;

    public EventBookingServlet(EventBookingService eventBookingService, SpringTemplateEngine templateEngine) {
        this.eventBookingService = eventBookingService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // we receive what the user has submitted in the form

        String ipAddress = req.getRemoteAddr();
        String attendee = req.getHeader("host");
        String eventName = req.getParameter("event");

        int numOfTickets = 0;
        String numTicketsParam = req.getParameter("numTickets");

        if (numTicketsParam != null && !numTicketsParam.isEmpty()) {
            try {
                numOfTickets = Integer.parseInt(numTicketsParam);
            } catch (NumberFormatException e) {
                numOfTickets = 0;
            }
        }

        // Create a WebContext for Thymeleaf processing
        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);

        if (eventName == null || eventName.isEmpty() || numOfTickets == 0) {
            // Set error message in context for rendering on the form page
            context.setVariable("errorMessage", "Please provide a valid event name and number of tickets.");

            templateEngine.process("bookingConfirmation.html", context, resp.getWriter());
            return;
        }

        eventBookingService.placeBooking(eventName, ipAddress, attendee, numOfTickets);

        context.setVariable("numOfTickets", numOfTickets);
        context.setVariable("event", eventName);
        context.setVariable("ipAddress", ipAddress);
        context.setVariable("attendee", attendee);

        templateEngine.process("bookingConfirmation.html", context, resp.getWriter());
    }
}
