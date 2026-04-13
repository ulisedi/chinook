<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Artist"%>

<%
    List<Artist> artists = (List<Artist>) request.getAttribute("artists");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    
    <title>Añadir Álbum</title>
   <%@ include file="/WEB-INF/head/head.jsp" %>
</head>
<body class="bg-light">

<!-- Header/Navbar -->
<nav class="navbar">
    <a href="${pageContext.request.contextPath}/index_crud.jsp" class="navbar-brand-link">
        <img src="${pageContext.request.contextPath}/img/logo.ico" alt="Chinook Logo" class="navbar-logo">
        <span class="navbar-brand"> Chinook</span>
    </a>
</nav>

<div class="container mt-5">
    <h2 class="mb-4">Añadir Álbum</h2>

    <form action="album" method="post">
        <input type="hidden" name="action" value="add">

        <div class="mb-3">
            <label for="title" class="form-label">Título del álbum</label>
            <input type="text" id="title" name="title" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="artistId" class="form-label">Artista</label>
            <select id="artistId" name="artistId" class="form-control" required>
                <option value="">-- Selecciona un artista --</option>
                <% if(artists != null) { %>
                    <% for(Artist a : artists) { %>
                        <option value="<%= a.getArtistId() %>"><%= a.getName() %></option>
                    <% } %>
                <% } %>
            </select>
        </div>

        <button type="submit" class="btn btn-success">Guardar</button>
        <a href="album" class="btn btn-secondary">Volver al listado</a>
    </form>
</div>
</body>
</html>
