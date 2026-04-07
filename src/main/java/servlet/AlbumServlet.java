package servlet;

import dao.AlbumDAO;
import dao.ArtistDAO;
import model.Album;
import model.Artist;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/album")
public class AlbumServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AlbumDAO dao;
    private ArtistDAO artistDAO;

    @Override
    public void init() {
        dao = new AlbumDAO();
        artistDAO = new ArtistDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "new":       // Mostrar formulario para nuevo álbum
                showNewForm(request, response);
                break;
            case "edit":
                editAlbum(request, response);
                break;
            case "delete":
                deleteAlbum(request, response);
                break;
            default:
                listAlbums(request, response);
                break;
        }
    }

    private void listAlbums(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Album> list = dao.getAllAlbum();
        request.setAttribute("albums", list);
        request.getRequestDispatcher("WEB-INF/views/albums.jsp")
               .forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Album album = new Album();
        List<Artist> artists = artistDAO.getAllArtists();
        request.setAttribute("album", album);
        request.setAttribute("artists", artists);
        request.getRequestDispatcher("WEB-INF/views/add_album.jsp")
               .forward(request, response);
    }

    private void editAlbum(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Album album = dao.getAlbumById(id);
            List<Artist> artists = artistDAO.getAllArtists();
            request.setAttribute("album", album);
            request.setAttribute("artists", artists);
            request.getRequestDispatcher("WEB-INF/views/edit_album.jsp")
                   .forward(request, response);
        } else {
            response.sendRedirect("album");
        }
    }

    private void deleteAlbum(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String idParam = request.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            dao.deleteAlbum(id);
        }

        response.sendRedirect("album");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "add":      // Mantener add
                addAlbum(request, response);
                break;
            case "update":
                updateAlbum(request, response);
                break;
            default:
                response.sendRedirect("album");
                break;
        }
    }

    private void addAlbum(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String title = request.getParameter("title");
        String artistIdParam = request.getParameter("artistId");
        
        if (title != null && !title.trim().isEmpty() && artistIdParam != null) {
            try {
                int artistId = Integer.parseInt(artistIdParam);
                dao.addAlbum(title.trim(), artistId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("album");
    }

    private void updateAlbum(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idParam = request.getParameter("id");
        String title = request.getParameter("title");
        
        if (idParam != null && title != null && !title.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                dao.updateAlbum(id, title.trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("album");
    }
}
