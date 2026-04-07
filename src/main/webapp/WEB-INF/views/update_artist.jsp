<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Artist" %>
<%
    Artist artist = (Artist) request.getAttribute("artist");
    if (artist == null) {
        // Redirige al servlet si no hay artista
        response.sendRedirect("artist");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    
    <title>Editar Artista</title>
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
    <h2 class="mb-4">Editar Artista</h2>

    <form action="artist" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= artist.getArtistId() %>">

        <div class="mb-3">
            <label class="form-label">Nombre del artista:</label>
            <input type="text" name="name" class="form-control" value="<%= artist.getName() %>" required>
        </div>

        <button type="submit" class="btn btn-primary">Actualizar</button>
        <a href="artist" class="btn btn-secondary">Cancelar</a>
    </form>

</div>

<!-- JS de tema noche/día -->
<script src="${pageContext.request.contextPath}/js/theme-toggle.js"></script>
</body>
</html>
