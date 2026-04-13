<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Album"%>

<%
    // La lista de albums debe venir desde el servlet
    List<Album> list = (List<Album>) request.getAttribute("albums");

    // Si no viene, redirigimos al servlet para obtenerla
    if(list == null){
        response.sendRedirect("album");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>

<title>Listado de Álbumes</title>
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

    <h2 class="mb-4">Listado de <span style="color: #00aae4;">Álbumes</span></h2>

    <div class="mb-3">
        <a href="index_crud.jsp" class="btn btn-secondary">Volver al menú</a>
        <a href="album?action=new" class="btn btn-success">Añadir Álbum</a>
    </div>

    <div class="search-sort-container">
        <div class="search-input-wrapper">
            <i class="fas fa-search"></i>
            <input type="text" id="album-search" class="form-control" placeholder="Buscar álbum...">
        </div>
        
        <div class="sort-options">
            <label for="sort-select">Ordenar por:</label>
            <select id="sort-select" class="form-control">
                <option value="">Selecciona una opción</option>
                <option value="id-asc">ID ↑ (Ascendente)</option>
                <option value="id-desc">ID ↓ (Descendente)</option>
                <option value="título-asc">Título ↑ (A-Z)</option>
                <option value="título-desc">Título ↓ (Z-A)</option>
                <option value="artista-asc">Artista ↑ (A-Z)</option>
                <option value="artista-desc">Artista ↓ (Z-A)</option>
            </select>
        </div>
    </div>

    <% if(list != null && !list.isEmpty()) { %>
    <table class="table table-striped">
        <thead class="table-dark">
            <tr>
                <th data-sortable>ID</th>
                <th data-sortable>Título</th>
                <th data-sortable>Artista</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody id="album-table-body">
            <% for(Album a : list) { %>
            <tr>
                <td><%= a.getAlbumId() %></td>
                <td><%= a.getTitle() %></td>
                <td><%= a.getArtistName() %></td>
                <td style="display: flex; gap: 8px; flex-wrap: wrap;">
                    <a href="album?action=edit&id=<%= a.getAlbumId() %>" class="btn btn-warning btn-sm">Editar</a>
                    <a href="album?action=delete&id=<%= a.getAlbumId() %>" class="btn btn-danger btn-sm">Borrar</a>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <% } else { %>
        <div class="alert alert-info">
            No hay álbumes disponibles.
        </div>
    <% } %>

</div>

</body>
</html>
<script src="${pageContext.request.contextPath}/js/search.js"></script>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const searchManager = new SearchManager();
        searchManager.init('album-search', 'album-table-body', 'sort-select');
    });
</script>

