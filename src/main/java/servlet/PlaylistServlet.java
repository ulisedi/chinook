package servlet;

import dao.PlaylistDAO;
import model.Playlist;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/playlist")
public class PlaylistServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private PlaylistDAO dao;

    @Override
    public void init() {
        dao = new PlaylistDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "new":       // Mostrar formulario para nuevo playlista
                showNewForm(request, response);
                break;
            case "edit":
                editPlaylist(request, response);
                break;
            case "delete":
                deletePlaylist(request, response);
                break;
            default:
                listPlaylists(request, response);
                break;
        }
    }

    private void listPlaylists(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Playlist> list = dao.getAllPlaylists();
        request.setAttribute("playlists", list);
        request.getRequestDispatcher("WEB-INF/views/playlists.jsp")
               .forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Playlist playlist = new Playlist();
        request.setAttribute("playlist", playlist);
        request.getRequestDispatcher("WEB-INF/views/add_playlist.jsp")
               .forward(request, response);
    }

    private void editPlaylist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Playlist playlist = dao.getPlaylistById(id);
            request.setAttribute("playlist", playlist);
            request.getRequestDispatcher("WEB-INF/views/edit_playlist.jsp")
                   .forward(request, response);
        } else {
            response.sendRedirect("playlist");
        }
    }

    private void deletePlaylist(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String idParam = request.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            dao.deletePlaylist(id);
        }

        response.sendRedirect("playlist");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "add":      // Mantener add
                addPlaylist(request, response);
                break;
            case "update":
                updatePlaylist(request, response);
                break;
            default:
                response.sendRedirect("playlist");
                break;
        }
    }

    private void addPlaylist(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        if (name != null && !name.trim().isEmpty()) {
            dao.addPlaylist(name.trim());
        }
        response.sendRedirect("playlist");
    }

    private void updatePlaylist(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        if (idParam != null && name != null && !name.trim().isEmpty()) {
            int id = Integer.parseInt(idParam);
            dao.updatePlaylist(id, name.trim());
        }
        response.sendRedirect("playlist");
    }
}