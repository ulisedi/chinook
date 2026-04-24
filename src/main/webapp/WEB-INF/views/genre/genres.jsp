<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Genre"%>

<%
    // La lista de generos debe venir desde el servlet
    List<Genre> list = (List<Genre>) request.getAttribute("genres");

    // Si no viene, redirigimos al servlet para obtenerla
    if(list == null){
        response.sendRedirect("genre");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>

<title>Listado de Generos</title>
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

    <h2 class="mb-4">Listado de <span style="color: #00aae4;">Géneros</span></h2>

    <div class="mb-3">
  
        <a href="index_crud.jsp" class="btn btn-secondary">Volver al menú</a>
        <a href="genre?action=new" class="btn btn-success">Añadir Genero</a>
    </div>

   
    <div class="search-sort-container">
        <div class="search-input-wrapper">
            <i class="fas fa-search"></i>
            <input type="text" id="genre-search" class="form-control" placeholder="Buscar género...">
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
        <tbody id="genre-table-body">
            <% for(Genre a : list) { %>
            <tr>
                <td><%= a.getGenreId() %></td>
                <td><%= a.getName() %></td>
                <td style="display: flex; gap: 8px; flex-wrap: wrap;">
                    <a href="genre?action=edit&id=<%= a.getGenreId() %>" class="btn btn-warning btn-sm">Editar</a>
                    <a href="genre?action=delete&id=<%= a.getGenreId() %>" class="btn btn-danger btn-sm">Borrar</a>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <% } else { %>
        <div class="alert alert-info">
            No hay generos disponibles.
        </div>
    <% } %>

</div>

</body>
</html>
<script src="${pageContext.request.contextPath}/js/search.js"></script>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const searchManager = new SearchManager();
        searchManager.init('genre-search', 'genre-table-body', 'sort-select');
    });
</script>
