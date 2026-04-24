<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Track"%>
<%@ page import="model.Artist"%>
<%@ page import="model.Genre"%>
<%@ page import="model.Album"%>

<%
    // La lista de tracks debe venir desde el servlet
    List<Track> list = (List<Track>) request.getAttribute("tracks");
    List<Artist> artists = (List<Artist>) request.getAttribute("artists");
    List<Genre> genres = (List<Genre>) request.getAttribute("genres");
    List<Album> albums = (List<Album>) request.getAttribute("albums");
    String filterType = (String) request.getAttribute("filterType");
    String filterId = (String) request.getAttribute("filterId");
    String filterName = (String) request.getAttribute("filterName");

    // Si no viene, redirigimos al servlet para obtenerla
    if(list == null){
        response.sendRedirect("track");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>

<title>Listado de Tracks</title>
<%@ include file="/WEB-INF/head/head.jsp" %>
</head>
<body class="bg-light">

<nav class="navbar">
    <a href="${pageContext.request.contextPath}/index_crud.jsp" class="navbar-brand-link">
        <img src="${pageContext.request.contextPath}/img/logo.ico" alt="Chinook Logo" class="navbar-logo">
        <span class="navbar-brand"> Chinook</span>
    </a>
</nav>

<div class="container mt-5">

    <h2 class="mb-4">Listado de <span style="color: #9b59b6;">Tracks</span></h2>

    <div class="mb-3">
        <a href="index_crud.jsp" class="btn btn-secondary">Volver al menú</a>
        <% if (filterType != null && !filterType.isEmpty()) { %>
            <a href="track" class="btn btn-info">Limpiar filtros</a>
            <span class="badge bg-primary ms-2">Filtro: <%= filterName %></span>
        <% } %>
    </div>

    <!-- Filtros -->
    <div class="card mb-4">
        <div class="card-body">
            <h5 class="card-title">Filtrar Tracks por:</h5>
            <div class="row">
                <div class="col-md-4 mb-2">
                    <label for="artistSelect" class="form-label">Por Artista:</label>
                    <select id="artistSelect" class="form-select" onchange="filterByArtist()">
                        <option value="">-- Seleccionar Artista --</option>
                        <% if (artists != null) {
                            for (Artist a : artists) { %>
                                <option value="<%= a.getArtistId() %>"><%= a.getName() %></option>
                            <% }
                        } %>
                    </select>
                </div>
                <div class="col-md-4 mb-2">
                    <label for="genreSelect" class="form-label">Por Género:</label>
                    <select id="genreSelect" class="form-select" onchange="filterByGenre()">
                        <option value="">-- Seleccionar Género --</option>
                        <% if (genres != null) {
                            for (Genre g : genres) { %>
                                <option value="<%= g.getGenreId() %>"><%= g.getName() %></option>
                            <% }
                        } %>
                    </select>
                </div>
                <div class="col-md-4 mb-2">
                    <label for="albumSelect" class="form-label">Por Álbum:</label>
                    <select id="albumSelect" class="form-select" onchange="filterByAlbum()">
                        <option value="">-- Seleccionar Álbum --</option>
                        <% if (albums != null) {
                            for (Album al : albums) { %>
                                <option value="<%= al.getAlbumId() %>"><%= al.getTitle() %></option>
                            <% }
                        } %>
                    </select>
                </div>
            </div>
        </div>
    </div>

    <!-- Búsqueda de texto -->
    <div class="search-sort-container">
        <div class="search-input-wrapper">
            <i class="fas fa-search"></i>
            <input type="text" id="track-search" class="form-control" placeholder="Buscar track...">
        </div>
    </div>

    <% if(list != null && !list.isEmpty()) { %>
    <table class="table table-striped">
        <thead class="table-dark">
            <tr>
                <th data-sortable>ID</th>
                <th data-sortable>Nombre</th>
                <th data-sortable>Artista</th>
                <th data-sortable>Álbum</th>
                <th data-sortable>Género</th>
                <th data-sortable>Precio</th>
            </tr>
        </thead>
        <tbody id="track-table-body">
            <% for(Track t : list) { %>
            <tr>
                <td><%= t.getTrackId() %></td>
                <td><%= t.getName() %></td>
                <td><%= t.getArtistName() %></td>
                <td><%= t.getAlbumTitle() %></td>
                <td><%= t.getGenreName() %></td>
                <td>$<%= String.format("%.2f", t.getUnitPrice()) %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <% } else { %>
        <div class="alert alert-info">
            No hay tracks disponibles con los filtros seleccionados.
        </div>
    <% } %>

</div>

</body>
</html>
<script src="${pageContext.request.contextPath}/js/search.js"></script>

<script>
    function filterByArtist() {
        const artistId = document.getElementById('artistSelect').value;
        if (artistId) {
            window.location.href = 'track?filterType=artist&filterId=' + artistId;
        } else {
            window.location.href = 'track';
        }
    }

    function filterByGenre() {
        const genreId = document.getElementById('genreSelect').value;
        if (genreId) {
            window.location.href = 'track?filterType=genre&filterId=' + genreId;
        } else {
            window.location.href = 'track';
        }
    }

    function filterByAlbum() {
        const albumId = document.getElementById('albumSelect').value;
        if (albumId) {
            window.location.href = 'track?filterType=album&filterId=' + albumId;
        } else {
            window.location.href = 'track';
        }
    }

    document.addEventListener('DOMContentLoaded', function() {
        const searchManager = new SearchManager();
        searchManager.init('track-search', 'track-table-body', 'sort-select');
    });
</script>
