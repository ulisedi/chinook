<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Album"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Artist"%>

<%
    Album album = (Album) request.getAttribute("album");
    List<Artist> artists = (List<Artist>) request.getAttribute("artists");
    
    if (album == null) {
        response.sendRedirect("album");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>

<title>Editar Álbum</title>
<%@ include file="/WEB-INF/head/head.jsp" %>
</head>
<body class="bg-light">

<!-- Header/Navbar -->
<nav class="navbar">
    <a href="${pageContext.request.contextPath}/index_crud.jsp" class="navbar-brand-link">
        <img src="${pageContext.request.contextPath}/img/logo.ico" alt="Chinook Logo" class="navbar-logo">
        <span class="navbar-brand"> Chinook</span>
    </a>
    <div style="flex-grow: 1;"></div>
    
    <!-- Toggle de tema -->
    <button class="theme-toggle-navbar" title="Cambiar a modo oscuro">
        <i class="fas fa-moon"></i>
    </button>
</nav>

<div class="container mt-5">
    <h2 class="mb-4">Editar Álbum</h2>

    <form action="album" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= album.getAlbumId() %>">

        <div class="mb-3">
            <label for="title" class="form-label">Título del álbum</label>
            <input type="text" id="title" name="title" class="form-control" value="<%= album.getTitle() %>" required>
        </div>

        <div class="mb-3">
            <label for="artistId" class="form-label">Artista</label>
            <select id="artistId" name="artistId" class="form-control" required>
                <option value="">-- Selecciona un artista --</option>
                <% if(artists != null) { %>
                    <% for(Artist a : artists) { %>
                        <% if(a.getArtistId() == album.getArtistId()) { %>
                            <option value="<%= a.getArtistId() %>" selected><%= a.getName() %></option>
                        <% } else { %>
                            <option value="<%= a.getArtistId() %>"><%= a.getName() %></option>
                        <% } %>
                    <% } %>
                <% } %>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Actualizar</button>
        <a href="album" class="btn btn-secondary">Volver al listado</a>
    </form>
</div>

<!-- JS de tema noche/día -->
<script src="${pageContext.request.contextPath}/js/theme-toggle.js"></script>
</body>
</html>
