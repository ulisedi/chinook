package servlet;

import dao.ArtistDAO;
import model.Artist;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

/**
 * SERVLET CONTROLADOR - ArtistServlet
 * Intercepta solicitudes HTTP del usuario
 * Procesa las acciones (listar, crear, editar, eliminar)
 * Comunica con DAO y envía respuestas a JSP
 */
@WebServlet("/artist")
public class ArtistServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    // DAO: Encargado de comunicarse con la Base de Datos
    private ArtistDAO dao;

    @Override
    public void init() {
        // Inicializar DAO una sola vez (cuando se crea el Servlet)
        dao = new ArtistDAO();
    }

    /**
     * doGet: Maneja solicitudes GET (ver datos, formularios)
     * El parámetro "action" determina qué hacer
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "new":
                // Mostrar formulario vacío para crear nuevo artista
                showNewForm(request, response);
                break;
            case "edit":
                // Cargar artista y mostrar formulario de edición
                editArtist(request, response);
                break;
            case "delete":
                // Eliminar artista de la BD
                deleteArtist(request, response);
                break;
            default:
                // Si no hay action, mostrar lista de todos los artistas
                listArtists(request, response);
                break;
        }
    }

    // Obtener lista completa de artistas y enviar a JSP
    private void listArtists(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Artist> list = dao.getAllArtists();
        request.setAttribute("artists", list);
        request.getRequestDispatcher("WEB-INF/views/artists.jsp")
               .forward(request, response);
    }

    // Preparar formulario vacío para nuevo artista
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Artist artist = new Artist();
        request.setAttribute("artist", artist);
        request.getRequestDispatcher("WEB-INF/views/add_artist.jsp")
               .forward(request, response);
    }

    // Buscar artista por ID y mostrar en formulario de edición
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

    // Eliminar artista y redirigir a lista
    private void deleteArtist(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String idParam = request.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            dao.deleteArtist(id);
        }

        response.sendRedirect("artist");
    }

    /**
     * doPost: Maneja solicitudes POST (guardar datos)
     * Recibe datos del formulario y actualiza la BD
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "add":
                // Insertar nuevo artista
                addArtist(request, response);
                break;
            case "update":
                // Actualizar artista existente
                updateArtist(request, response);
                break;
            default:
                response.sendRedirect("artist");
                break;
        }
    }

    // Insertar artista en BD
    private void addArtist(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        if (name != null && !name.trim().isEmpty()) {
            dao.addArtist(name.trim());
        }
        response.sendRedirect("artist");
    }

    // Actualizar artista en BD
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