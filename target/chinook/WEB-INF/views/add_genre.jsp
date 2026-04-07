<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    
    <title>Añadir Artista</title>
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
    <h2 class="mb-4">Añadir Genero</h2>

    <form action="genre" method="post">
        <input type="hidden" name="action" value="add">

        <div class="mb-3">
            <label for="name" class="form-label">Nombre del genero</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-success">Guardar</button>
        <a href="genre" class="btn btn-secondary">Volver al listado</a>
    </form>
</div>

<!-- JS de tema noche/día -->
<script src="${pageContext.request.contextPath}/js/theme-toggle.js"></script>
</body>
</html>