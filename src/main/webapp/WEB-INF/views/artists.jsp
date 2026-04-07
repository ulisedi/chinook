<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Artist"%>

<%
    // La lista de artistas debe venir desde el servlet
    List<Artist> list = (List<Artist>) request.getAttribute("artists");

    // Si no viene, redirigimos al servlet para obtenerla
    if(list == null){
        response.sendRedirect("artist");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>

<title>Listado de Artistas</title>
<%@ include file="/WEB-INF/head/head.jsp" %>
</head>
<body class="bg-light">


<nav class="navbar">
    <a href="${pageContext.request.contextPath}/index_crud.jsp" class="navbar-brand-link">
        <img src="${pageContext.request.contextPath}/img/logo.ico" alt="Chinook Logo" class="navbar-logo">
        <span class="navbar-brand"> Chinook</span>
    </a>
    <button class="theme-toggle-navbar" title="Cambiar a modo oscuro">
        <i class="fas fa-moon"></i>
    </button>
</nav>

<div class="container mt-5">

    <h2 class="mb-4">Listado de <span style="color: #00aae4;">Artistas</span></h2>

    <div class="mb-3">
   
        <a href="index_crud.jsp" class="btn btn-secondary">Volver al menú</a>
        <a href="artist?action=new" class="btn btn-success">Añadir Artista</a>
    </div>

    <div class="search-sort-container">
        <div class="search-input-wrapper">
            <i class="fas fa-search"></i>
            <input type="text" id="artist-search" class="form-control" placeholder="Buscar artista...">
        </div>
    </div>

    <% if(list != null && !list.isEmpty()) { %>
    <table class="table table-striped">
        <thead class="table-dark">
            <tr>
                <th data-sortable>ID</th>
                <th data-sortable>Nombre</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody id="artist-table-body">
            <% for(Artist a : list) { %>
            <tr>
                <td><%= a.getArtistId() %></td>
                <td><%= a.getName() %></td>
                <td>
                    <a href="artist?action=edit&id=<%= a.getArtistId() %>" class="btn btn-warning btn-sm">Editar</a>
                    <a href="artist?action=delete&id=<%= a.getArtistId() %>" class="btn btn-danger btn-sm">Borrar</a>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <% } else { %>
        <div class="alert alert-info">
            No hay artistas disponibles.
        </div>
    <% } %>

</div>

</body>
</html>
<script src="${pageContext.request.contextPath}/js/search.js"></script>
<script src="${pageContext.request.contextPath}/js/theme-toggle.js"></script>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const searchManager = new SearchManager();
        searchManager.init('artist-search', 'artist-table-body', 'sort-select');
    });
</script>

