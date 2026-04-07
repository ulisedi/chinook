package servlet;

import dao.GenreDAO;
import model.Genre;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/genre")
public class GenreServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private GenreDAO dao;

    @Override
    public void init() {
        dao = new GenreDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "new":       // Mostrar formulario para nuevo Genero
                showNewForm(request, response);
                break;
            case "edit":
                editGenre(request, response);
                break;
            case "delete":
                deleteGenre(request, response);
                break;
            default:
                listGenres(request, response);
                break;
        }
    }

    private void listGenres(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Genre> list = dao.getAllGenre();
        request.setAttribute("genres", list);
        request.getRequestDispatcher("WEB-INF/views/genres.jsp")
               .forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Genre genre = new Genre();
        request.setAttribute("genre", genre);
        request.getRequestDispatcher("WEB-INF/views/add_genre.jsp")
               .forward(request, response);
    }

    private void editGenre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Genre genre = dao.getGenreById(id);
            request.setAttribute("genre", genre);
            request.getRequestDispatcher("WEB-INF/views/edit_genre.jsp")
                   .forward(request, response);
        } else {
            response.sendRedirect("genre");
        }
    }

    private void deleteGenre(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String idParam = request.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            dao.deleteGenre(id);
        }

        response.sendRedirect("genre");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "add":      // Mantener add
                addGenre(request, response);
                break;
            case "update":
                updateGenre(request, response);
                break;
            default:
                response.sendRedirect("genre");
                break;
        }
    }

    private void addGenre(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        if (name != null && !name.trim().isEmpty()) {
            dao.addGenre(name.trim());
        }
        response.sendRedirect("genre");
    }

    private void updateGenre(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        if (idParam != null && name != null && !name.trim().isEmpty()) {
            int id = Integer.parseInt(idParam);
            dao.updateGenre(id, name.trim());
        }
        response.sendRedirect("genre");
    }
}