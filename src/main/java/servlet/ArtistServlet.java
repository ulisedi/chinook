package servlet;

import dao.ArtistDAO;
import model.Artist;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/artist")
public class ArtistServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ArtistDAO dao;

    @Override
    public void init() {
        dao = new ArtistDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "new":       // Mostrar formulario para nuevo artista
                showNewForm(request, response);
                break;
            case "edit":
                editArtist(request, response);
                break;
            case "delete":
                deleteArtist(request, response);
                break;
            default:
                listArtists(request, response);
                break;
        }
    }

    private void listArtists(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Artist> list = dao.getAllArtists();
        request.setAttribute("artists", list);
        request.getRequestDispatcher("WEB-INF/views/artists.jsp")
               .forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Artist artist = new Artist();
        request.setAttribute("artist", artist);
        request.getRequestDispatcher("WEB-INF/views/add_artist.jsp")
               .forward(request, response);
    }

    private void editArtist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Artist artist = dao.getArtistById(id);
            request.setAttribute("artist", artist);
            request.getRequestDispatcher("WEB-INF/views/edit_artist.jsp")
                   .forward(request, response);
        } else {
            response.sendRedirect("artist");
        }
    }

    private void deleteArtist(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String idParam = request.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            dao.deleteArtist(id);
        }

        response.sendRedirect("artist");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "add":      // Mantener add
                addArtist(request, response);
                break;
            case "update":
                updateArtist(request, response);
                break;
            default:
                response.sendRedirect("artist");
                break;
        }
    }

    private void addArtist(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        if (name != null && !name.trim().isEmpty()) {
            dao.addArtist(name.trim());
        }
        response.sendRedirect("artist");
    }

    private void updateArtist(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        if (idParam != null && name != null && !name.trim().isEmpty()) {
            int id = Integer.parseInt(idParam);
            dao.updateArtist(id, name.trim());
        }
        response.sendRedirect("artist");
    }
}