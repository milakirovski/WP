package mk.ukim.finki.wp.labb.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.labb.service.ArtistService;
import mk.ukim.finki.wp.labb.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "АrtistServlet", urlPatterns = {"/artist"})
public class ArtistServlet extends HttpServlet {
    private final ArtistService artistService;
    private final SongService songService;
    private final SpringTemplateEngine templateEngine;

    public ArtistServlet(ArtistService artistService, SongService songService, SpringTemplateEngine templateEngine) {
        this.artistService = artistService;
        this.songService = songService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String trackId = req.getParameter("trackId");

        //ke ja postavime trackeId vo servlet context-ot za da moze posle SongDetails Servlet da ja pristapi =)
        getServletContext().setAttribute("trackIdGlobal", trackId);

        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(req.getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(iWebExchange);

        context.setVariable("artists", this.artistService.listArtists());
        context.setVariable("trackId", trackId);

        templateEngine.process("artistsList.html", context, resp.getWriter());

    }
}
