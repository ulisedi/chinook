<%@page import="model.Artist"%>
<%
    Artist artist = (Artist) request.getAttribute("artist");
    if (artist == null) {
        // Si no hay artista, volvemos al listado pasando por el servlet
        response.sendRedirect("artist");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>

<title>Editar artista</title>
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
    <h2 class="mb-4">Editar artista</h2>

    <div class="card">
        <div class="card-body">

            <form action="artist" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= artist.getArtistId() %>">

                <div class="mb-3">
                    <label class="form-label">Nombre del artista</label>
                    <input type="text" name="name" class="form-control" value="<%= artist.getName() %>" required>
                </div>

                <button type="submit" class="btn btn-primary">Actualizar</button>
                <a href="artist" class="btn btn-secondary">Cancelar</a>
            </form>

        </div>
    </div>
</div>

<!-- JS de tema noche/día -->
</body>
</html>
