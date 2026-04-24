package servlet;

import dao.TrackDAO;
import dao.ArtistDAO;
import dao.GenreDAO;
import dao.AlbumDAO;
import model.Track;
import model.Artist;
import model.Genre;
import model.Album;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/track")
public class TrackServlet extends HttpServlet {

    private TrackDAO trackDAO;
    private ArtistDAO artistDAO;
    private GenreDAO genreDAO;
    private AlbumDAO albumDAO;

    @Override
    public void init() throws ServletException {
        trackDAO = new TrackDAO();
        artistDAO = new ArtistDAO();
        genreDAO = new GenreDAO();
        albumDAO = new AlbumDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null || action.isEmpty() || action.equals("list")) {
            listTracks(request, response);
        } else if (action.equals("detail")) {
            viewTrack(request, response);
        }
    }

    private void listTracks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Track> tracks = new ArrayList<>();
        String filterType = request.getParameter("filterType");
        String filterId = request.getParameter("filterId");
        String filterName = "";

        // Aplicar filtros si existen
        if (filterType != null && filterId != null && !filterId.isEmpty()) {
            int id = Integer.parseInt(filterId);
            
            if ("artist".equals(filterType)) {
                tracks = trackDAO.getTracksByArtist(id);
                Artist artist = artistDAO.getArtistById(id);
                filterName = artist != null ? artist.getName() : "Desconocido";
            } else if ("genre".equals(filterType)) {
                tracks = trackDAO.getTracksByGenre(id);
                Genre genre = genreDAO.getGenreById(id);
                filterName = genre != null ? genre.getName() : "Desconocido";
            } else if ("album".equals(filterType)) {
                tracks = trackDAO.getTracksByAlbum(id);
                Album album = albumDAO.getAlbumById(id);
                filterName = album != null ? album.getTitle() : "Desconocido";
            }
        } else {
            // Si no hay filtro, obtener todos los tracks
            tracks = trackDAO.getAllTracks();
        }

        // Obtener listas para los filtros dropdown
        List<Artist> artists = artistDAO.getAllArtists();
        List<Genre> genres = genreDAO.getAllGenre();
        List<Album> albums = albumDAO.getAllAlbum();

        request.setAttribute("tracks", tracks);
        request.setAttribute("artists", artists);
        request.setAttribute("genres", genres);
        request.setAttribute("albums", albums);
        request.setAttribute("filterType", filterType);
        request.setAttribute("filterId", filterId);
        request.setAttribute("filterName", filterName);

        request.getRequestDispatcher("/WEB-INF/views/track-list.jsp").forward(request, response);
    }

    private void viewTrack(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Track track = trackDAO.getTrackById(id);
        request.setAttribute("track", track);
        request.getRequestDispatcher("/WEB-INF/views/track-detail.jsp").forward(request, response);
    }
}
