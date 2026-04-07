<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    
    <title>Borrar Artista</title>
    <%@ include file="/WEB-INF/head/head.jsp" %>
</head>
<body class="bg-light">

<!-- Header/Navbar -->
<nav class="navbar">
    <a href="${pageContext.request.contextPath}/index_crud.jsp" class="navbar-brand-link">
        <img src="${pageContext.request.contextPath}/img/logo.ico" alt="Chinook Logo" class="navbar-logo">
        <span class="navbar-brand">▶ Chinook</span>
    </a>
    <div style="flex-grow: 1;"></div>
    
    <!-- Toggle de tema -->
    <button class="theme-toggle-navbar" title="Cambiar a modo oscuro">
        <i class="fas fa-moon"></i>
    </button>
</nav>

<div class="container mt-5">
    <h2 class="mb-4">Borrar Artista</h2>

    <% 
        // Obtenemos el id del artista a borrar
        String idParam = request.getParameter("id");
        if (idParam != null) {
    %>
        <!-- Botón que redirige al servlet para borrar directamente -->
        <a href="artist?action=delete&id=<%= idParam %>" class="btn btn-danger">
            Borrar artista
        </a>
        <a href="artist" class="btn btn-secondary">
            Cancelar
        </a>
    <% 
        } else { 
    %>
        <div class="alert alert-warning">
            No se ha recibido ID de artista.
        </div>
        <a href="artist" class="btn btn-secondary">Volver al listado</a>
    <% } %>

</div>

<!-- JS de tema noche/día -->
<script src="${pageContext.request.contextPath}/js/theme-toggle.js"></script>
</body>
</html>