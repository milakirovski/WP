package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EventListServlet", urlPatterns = {"/event-list"})
public class EventListServlet extends HttpServlet {

    private final EventService eventService;
    private final SpringTemplateEngine templateEngine;

    public EventListServlet(EventService eventService, SpringTemplateEngine templateEngine) {
        this.eventService = eventService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(req.getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(iWebExchange);

        // for search
        String searchString = req.getParameter("searchName");
        String searchRating = req.getParameter("searchRating");
        double searchRatingDouble = 0.0;

        //setirame variabli vo context-ot
        context.setVariable("events", this.eventService.listAll());

        if(searchString != null && searchRating != null && !searchString.isEmpty() && !searchRating.isEmpty()) {
            searchString = searchString.trim();
            searchRatingDouble = Double.parseDouble(searchRating.trim());
        }else {
            templateEngine.process("listEvents.html", context, resp.getWriter());
            return;
        }

        context.setVariable("filterEventsByName", this.eventService.searchEventsByName(searchString));
        context.setVariable("filterEventsByRating", this.eventService.searchEventByRating(searchRatingDouble));

        context.setVariable("searchString", searchString);
        context.setVariable("searchRating", searchRating);



        templateEngine.process("listEvents.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}