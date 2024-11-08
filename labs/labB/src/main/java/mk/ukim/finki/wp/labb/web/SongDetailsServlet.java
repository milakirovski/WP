package mk.ukim.finki.wp.labb.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.labb.model.Artist;
import mk.ukim.finki.wp.labb.model.Song;
import mk.ukim.finki.wp.labb.service.ArtistService;
import mk.ukim.finki.wp.labb.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "SongDetailsServlet", urlPatterns = {"/songDetails"})
public class SongDetailsServlet extends HttpServlet {

    private final SpringTemplateEngine templateEngine;
    private final SongService songService;
    private final ArtistService artistService;

    public SongDetailsServlet(SpringTemplateEngine templateEngine, SongService songService, ArtistService artistService) {
        this.templateEngine = templateEngine;
        this.songService = songService;
        this.artistService = artistService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(req.getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(iWebExchange);

        // gather the data collected from the form and from the ServletContext
        String trackId = (String) getServletContext().getAttribute("trackIdGlobal");
        String artistId = req.getParameter("artistId");

        Optional<Artist> artist = artistService.findById(Long.parseLong(artistId.trim()));

        if(artist.isEmpty()){
            context.setVariable("nullArgs","No track or artist was selected!");
        }else{
            //add the artist to the song
            Song theSong = songService.findByTrackId(trackId);
            songService.addArtistToSong(artist.get(), theSong);

            // add to the context as variables
            context.setVariable("songDetails", theSong);
//            context.setVariable("trackIdG", trackId);
        }

        templateEngine.process("songDetails.html", context, resp.getWriter());
    }
}
