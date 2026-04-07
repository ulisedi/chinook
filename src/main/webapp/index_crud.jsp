<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html lang="es">

    <head>
        <%@ include file="/WEB-INF/head/head.jsp" %>
            <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet">
            <title>Chinook - CRUD Gestión</title>
    </head>

    <body>

        <!-- Header/Navbar -->
        <nav class="navbar">
            <a href="index_crud.jsp" class="navbar-brand-link">
                <img src="${pageContext.request.contextPath}/img/logo.ico" alt="Chinook Logo" class="navbar-logo">
                <span class="navbar-brand"> Chinook</span>
            </a>
            <div class="navbar-spacer"></div>

            <!-- Toggle de tema -->
            <button class="theme-toggle-navbar" title="Cambiar a modo oscuro">
                <i class="fas fa-moon"></i>
            </button>
        </nav>

        <div class="container">

            <h1 class="mb-4 index-title">Bienvenido a Chinook</h1>

            <div class="cards-container">

                <!-- Card Artistas -->
                <div class="card card-section">
                    <div class="card-body text-center">
                        <h3 class="card-title-primary " style="color: red;"> Artistas</h3>

                        <a href="artist" class="btn btn-primary card-button">Ver Artistas</a>
                    </div>
                </div>

                <!-- Card Géneros -->
                <div class="card card-section">
                    <div class="card-body text-center">
                        <h3 class="card-title-secondary" style="color: green;"> Géneros</h3>

                        <a href="genre" class="btn btn-primary card-button">Ver Géneros</a>
                    </div>
                </div>

                <!-- Card Álbumes -->
                <div class="card card-section">
                    <div class="card-body text-center">
                        <h3 class="card-title-warning" style="color: orange;"> Álbumes</h3>

                        <a href="album" class="btn btn-primary card-button">Ver Álbumes</a>
                    </div>
                </div>

                <!-- Card Playlists -->
                <div class="card card-section">
                    <div class="card-body text-center">
                        <h3 class="card-title-danger" style="color: blue;"> Playlists</h3>

                        <a href="playlist" class="btn btn-primary card-button">Ver Playlists</a>
                    </div>
                </div>

            </div>

        </div>

    </body>

    </html>
    <!-- JS de Bootstrap local -->
    <script src="js/bootstrap.bundle.min.js"></script>
    <!-- JS de tema noche/día -->
    <script src="js/theme-toggle.js"></script>
    <!-- JS de búsqueda -->
    <script src="js/search.js"></script>