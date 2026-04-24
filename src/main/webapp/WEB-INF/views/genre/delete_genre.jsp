<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
   
    <title>Borrar Género</title>
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
    <h2 class="mb-4">Borrar Género</h2>

    <% 
        // Obtenemos el id del Género a borrar
        String idParam = request.getParameter("id");
        if (idParam != null) {
    %>
        <!-- Botón que redirige al servlet para borrar directamente -->
        <a href="genre?action=delete&id=<%= idParam %>" class="btn btn-danger">
            Borrar Género
        </a>
        <a href="genre" class="btn btn-secondary">
            Cancelar
        </a>
    <% 
        } else { 
    %>
        <div class="alert alert-warning">
            No se ha recibido ID del género.
        </div>
        <a href="genre" class="btn btn-secondary">Volver al listado</a>
    <% } %>

</div>

<!-- JS de tema noche/día -->
</body>
</html>
