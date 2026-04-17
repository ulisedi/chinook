# 🎤 GUÍA DE EXPOSICIÓN - Proyecto Chinook CRUD

## Documento para Presentación y Explicación de Partes Fundamentales

---

# 📋 TABLA DE CONTENIDOS

1. [Introducción al Proyecto](#1-introducción-al-proyecto)
2. [Visión General de Arquitectura](#2-visión-general-de-arquitectura)
3. [Tecnologías Utilizadas](#3-tecnologías-utilizadas)
4. [Base de Datos](#4-base-de-datos)
5. [Patrón MVC](#5-patrón-mvc)
6. [Capas del Proyecto](#6-capas-del-proyecto)
7. [Flujo de Solicitud](#7-flujo-de-solicitud)
8. [Ejemplos de Código](#8-ejemplos-de-código)
9. [Funcionalidades Principales](#9-funcionalidades-principales)
10. [Instalación y Ejecución](#10-instalación-y-ejecución)

---

# 1. INTRODUCCIÓN AL PROYECTO

## 🎵 ¿Qué es Chinook CRUD?

**Chinook CRUD** es una aplicación web educativa de gestión de base de datos musical desarrollada con **Java**, **Jakarta Servlets**, **JSP** y **MySQL**.

### Propósito
- Demostrar la implementación del patrón **MVC** (Model-View-Controller)
- Mostrar operaciones CRUD (Create, Read, Update, Delete) en una base de datos
- Implementar una interfaz web moderna e interactiva
- Practicar conceptos de programación web en Java

### Público Objetivo
- Estudiantes de programación
- Desarrolladores principiantes en Java Web
- Profesionales que necesitan entender arquitectura web

### Áreas de Aprendizaje
✅ Programación en Java  
✅ Servlets y JSP  
✅ Acceso a bases de datos (JDBC)  
✅ Patrón MVC  
✅ HTML, CSS, JavaScript  
✅ Bootstrap y diseño responsivo  

---

# 2. VISIÓN GENERAL DE ARQUITECTURA

## 🏗️ Estructura General

```
┌─────────────────────────────────────────────────────────────┐
│                    NAVEGADOR DEL USUARIO                     │
└───────────────────────┬─────────────────────────────────────┘
                        │ HTTP Solicitud
                        ▼
┌─────────────────────────────────────────────────────────────┐
│                    SERVIDOR TOMCAT                           │
│  ┌────────────────────────────────────────────────────────┐ │
│  │              CAPA DE PRESENTACIÓN (VIEW)               │ │
│  │  JSP Files: artists.jsp, albums.jsp, genres.jsp, ... │ │
│  │  CSS: ystyle.css, bootstrap.css                       │ │
│  │  JS: search.js, bootstrap.js                          │ │
│  └────────────────────────────────────────────────────────┘ │
│                        │
│                        │ REQUEST/RESPONSE
│                        ▼
│  ┌────────────────────────────────────────────────────────┐ │
│  │           CAPA DE CONTROL (CONTROLLER)                 │ │
│  │  Servlets: ArtistServlet, AlbumServlet, ...           │ │
│  │  Responsabilidad: Procesar solicitudes HTTP           │ │
│  └────────────────────────────────────────────────────────┘ │
│                        │
│                        │ LÓGICA DE NEGOCIO
│                        ▼
│  ┌────────────────────────────────────────────────────────┐ │
│  │             CAPA DE DATOS (MODEL + DAO)               │ │
│  │  Models: Artist.java, Album.java, Genre.java, ...    │ │
│  │  DAOs: ArtistDAO.java, AlbumDAO.java, ...             │ │
│  │  Utilidades: DBConnection.java                        │ │
│  └────────────────────────────────────────────────────────┘ │
│                        │
│                        │ JDBC/SQL
│                        ▼
└─────────────────────────────────────────────────────────────┘
                        │
                        ▼
┌─────────────────────────────────────────────────────────────┐
│                    MYSQL DATABASE                            │
│  Tables: artists, albums, genres, playlists, ...            │
└─────────────────────────────────────────────────────────────┘
```

### Componentes Principales

| Capa | Componentes | Responsabilidad |
|------|-------------|-----------------|
| **Presentación** | JSP, HTML, CSS, JS | Mostrar interfaz al usuario |
| **Controlador** | Servlets | Procesar solicitudes HTTP |
| **Modelo** | Clases Java | Representar datos de negocio |
| **Acceso a Datos** | DAO | Comunicación con BD |
| **Utilidades** | DBConnection | Gestionar conexiones BD |

---

# 3. TECNOLOGÍAS UTILIZADAS

## 🛠️ Stack Tecnológico

### Backend
```
┌─────────────────────────────────────┐
│       JAVA 17                       │
│  - Lenguaje de programación         │
│  - Compilado y tipado               │
│  - Orientado a objetos              │
└─────────────────────────────────────┘
           │
           ▼
┌─────────────────────────────────────┐
│  JAKARTA SERVLET API 5.0.0          │
│  - Manejo de solicitudes HTTP       │
│  - Servlets como controladores      │
│  - Request/Response                 │
└─────────────────────────────────────┘
           │
           ▼
┌─────────────────────────────────────┐
│  JAKARTA JSP 3.0.0                  │
│  - Plantillas dinámicas HTML        │
│  - Mezcla Java + HTML               │
│  - Expresiones EL                   │
└─────────────────────────────────────┘
           │
           ▼
┌─────────────────────────────────────┐
│  MYSQL CONNECTOR J 8.3.0            │
│  - Driver JDBC para MySQL           │
│  - PreparedStatements               │
│  - Connection Pooling               │
└─────────────────────────────────────┘
```

### Frontend
```
HTML5          - Estructura
CSS3           - Estilos
Bootstrap 5.3  - Framework CSS
Font Awesome   - Iconos
JavaScript ES6 - Interactividad
```

### Servidor
```
Apache Tomcat 11.0.18
- Servidor de aplicaciones
- Contenedor de servlets
- Manejo de sesiones
```

### Base de Datos
```
MySQL 8.0+
- Base de datos relacional
- Tablas: artists, albums, genres, playlists
- Relaciones: Foreign Keys
```

### Herramientas
```
Maven 3.6+         - Gestor de dependencias
Git                - Control de versiones
Java IDE           - Editor de código
```

---

# 4. BASE DE DATOS

## 🗄️ Estructura de Datos

### Tabla: artists
```sql
CREATE TABLE artists (
    ArtistId INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(120) NOT NULL
);
```

**Propósito:** Almacenar información de artistas musicales

**Ejemplo de datos:**
```
| ArtistId | Name           |
|----------|----------------|
| 1        | AC/DC          |
| 2        | Accept         |
| 3        | Aerosmith      |
```

---

### Tabla: genres
```sql
CREATE TABLE genres (
    GenreId INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(120) NOT NULL
);
```

**Propósito:** Almacenar géneros musicales

**Ejemplo de datos:**
```
| GenreId | Name  |
|---------|-------|
| 1       | Rock  |
| 2       | Pop   |
| 3       | Jazz  |
```

---

### Tabla: albums
```sql
CREATE TABLE albums (
    AlbumId INT PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(160) NOT NULL,
    ArtistId INT NOT NULL,
    FOREIGN KEY (ArtistId) REFERENCES artists(ArtistId)
);
```

**Propósito:** Almacenar álbumes musicales

**Características:**
- Relación 1:N con artists
- Un artista puede tener múltiples álbumes
- Integridad referencial mediante Foreign Key

**Ejemplo de datos:**
```
| AlbumId | Title            | ArtistId |
|---------|------------------|----------|
| 1       | Back in Black    | 1        |
| 2       | Restless & Wild  | 2        |
```

---

### Tabla: playlists
```sql
CREATE TABLE playlists (
    PlaylistId INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(120) NOT NULL
);
```

**Propósito:** Almacenar playlists creadas por usuarios

---

### Relaciones de Base de Datos

```
┌──────────────┐           ┌──────────────┐
│   artists    │─────┬────▶│   albums     │
│              │     │     │              │
│ ArtistId (PK)│     │     │ AlbumId (PK) │
│ Name         │     │     │ Title        │
└──────────────┘     │     │ ArtistId (FK)│
                     │     └──────────────┘
                     │
              (1 a N)
          (Un artista puede
           tener muchos
           álbumes)
```

---

# 5. PATRÓN MVC

## 🎬 Model-View-Controller

### ¿Qué es MVC?

MVC es un patrón arquitectónico que separa una aplicación en tres capas interconectadas:

```
    ┌─────────────────────┐
    │      MODEL          │
    │                     │
    │ - Datos             │
    │ - Lógica de negocio │
    │ - Validación        │
    └──────────┬──────────┘
               │
       ┌───────┴───────┐
       │               │
       ▼               ▼
   ┌────────┐    ┌──────────┐
   │  VIEW  │    │ CONTROLLER
   │        │    │
   │- HTML  │    │- Procesa
   │- CSS   │    │  requests
   │- JS    │    │- Actualiza
   └────────┘    │  model
       ▲         │- Redirige
       │         │  a views
       └─────────┘
```

### Ventajas del Patrón MVC

✅ **Separación de responsabilidades**
- Cada capa tiene un propósito específico
- Cambios en una capa no afectan otras

✅ **Reutilización de código**
- Modelos se pueden reutilizar
- Vistas pueden compartir lógica

✅ **Testabilidad**
- Cada capa se puede probar independientemente
- Código más limpio y mantenible

✅ **Escalabilidad**
- Fácil agregar nuevas funcionalidades
- Estructura clara y organizada

---

## 📦 Implementación en Chinook

### 1. MODEL (Capa de Datos)

**Ubicación:** `src/main/java/model/`

```java
// Ejemplo: Artist.java
public class Artist {
    private int artistId;
    private String name;
    
    // Constructores
    public Artist() {}
    
    public Artist(int artistId, String name) {
        this.artistId = artistId;
        this.name = name;
    }
    
    // Getters y Setters
    public int getArtistId() { return artistId; }
    public void setArtistId(int id) { this.artistId = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```

**Responsabilidades:**
- Representar entidades del negocio
- Almacenar datos
- No contiene lógica de presentación

---

### 2. VIEW (Capa de Presentación)

**Ubicación:** `src/main/webapp/WEB-INF/views/`

```jsp
<!-- Ejemplo: artists.jsp -->
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Listado de Artistas</title>
    <link href="${pageContext.request.contextPath}/css/ystyle.css" 
          rel="stylesheet">
</head>
<body>
    <h1>Artistas</h1>
    
    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${artists}" var="artist">
                <tr>
                    <td>${artist.artistId}</td>
                    <td>${artist.name}</td>
                    <td>
                        <a href="artist?action=edit&id=${artist.artistId}">
                            Editar
                        </a>
                        <a href="artist?action=delete&id=${artist.artistId}">
                            Borrar
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
```

**Responsabilidades:**
- Presentar datos al usuario
- Formularios de entrada
- No contiene lógica de negocio

---

### 3. CONTROLLER (Capa de Control)

**Ubicación:** `src/main/java/servlet/`

```java
// Ejemplo: ArtistServlet.java
@WebServlet("/artist")
public class ArtistServlet extends HttpServlet {
    
    private ArtistDAO artistDAO = new ArtistDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, 
                        HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        // Listar todos los artistas
        if (action == null) {
            List<Artist> artists = artistDAO.getAllArtists();
            request.setAttribute("artists", artists);
            request.getRequestDispatcher("/WEB-INF/views/artists.jsp")
                .forward(request, response);
        }
        
        // Mostrar formulario de edición
        else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Artist artist = artistDAO.getArtistById(id);
            request.setAttribute("artist", artist);
            request.getRequestDispatcher("/WEB-INF/views/edit_artist.jsp")
                .forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, 
                         HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        // Crear nuevo artista
        if ("add".equals(action)) {
            String name = request.getParameter("name");
            artistDAO.addArtist(name);
        }
        
        // Actualizar artista
        else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            artistDAO.updateArtist(id, name);
        }
        
        // Redireccionar después de guardar
        response.sendRedirect("artist");
    }
}
```

**Responsabilidades:**
- Recibir solicitudes HTTP
- Procesar parámetros
- Coordinar entre Model y View
- Manejar lógica de flujo

---

# 6. CAPAS DEL PROYECTO

## 📂 Estructura de Archivos

```
src/main/java/
├── dao/                          ← CAPA DE ACCESO A DATOS
│   ├── ArtistDAO.java           ├─ Consultas SQL
│   ├── AlbumDAO.java            ├─ Validación de datos
│   ├── GenreDAO.java            ├─ Manejo de conexiones
│   └── PlaylistDAO.java         └─ Transformación de datos
│
├── model/                         ← CAPA DE MODELOS
│   ├── Artist.java              ├─ Clases POJO
│   ├── Album.java               ├─ Getters/Setters
│   ├── Genre.java               ├─ Constructores
│   └── Playlist.java            └─ Representación de datos
│
├── servlet/                       ← CAPA DE CONTROL
│   ├── ArtistServlet.java       ├─ Manejo de requests
│   ├── AlbumServlet.java        ├─ Lógica de flujo
│   ├── GenreServlet.java        ├─ Redirecciones
│   └── PlaylistServlet.java     └─ Atributos de request
│
└── util/                          ← CAPA DE UTILIDADES
    └── DBConnection.java         └─ Gestión de conexiones
```

### Responsabilidades por Capa

#### 🔹 Capa DAO (Data Access Object)

```java
public class ArtistDAO {
    
    // Obtener todos los artistas
    public List<Artist> getAllArtists() {
        // SELECT * FROM artists
    }
    
    // Obtener artista por ID
    public Artist getArtistById(int id) {
        // SELECT * FROM artists WHERE ArtistId = id
    }
    
    // Insertar nuevo artista
    public void addArtist(String name) {
        // INSERT INTO artists (Name) VALUES (name)
    }
    
    // Actualizar artista
    public void updateArtist(int id, String name) {
        // UPDATE artists SET Name = name WHERE ArtistId = id
    }
    
    // Eliminar artista
    public void deleteArtist(int id) {
        // DELETE FROM artists WHERE ArtistId = id
    }
}
```

**Características:**
- ✅ Encapsula acceso a base de datos
- ✅ Usa PreparedStatements para seguridad
- ✅ Manejo de excepciones
- ✅ Transformación de ResultSet a objetos

---

#### 🔹 Capa Model

```java
public class Artist {
    // Atributos privados
    private int artistId;
    private String name;
    
    // Constructor sin parámetros
    public Artist() {}
    
    // Constructor con parámetros
    public Artist(int artistId, String name) {
        this.artistId = artistId;
        this.name = name;
    }
    
    // Métodos getter y setter
    public int getArtistId() { return artistId; }
    public void setArtistId(int artistId) { this.artistId = artistId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```

**Características:**
- ✅ POJO (Plain Old Java Object)
- ✅ Serializable
- ✅ Encapsulación de datos
- ✅ Sin lógica de negocio compleja

---

#### 🔹 Capa Servlet (Controller)

```java
@WebServlet("/artist")
public class ArtistServlet extends HttpServlet {
    
    private ArtistDAO artistDAO = new ArtistDAO();
    
    // Maneja solicitudes GET
    protected void doGet(HttpServletRequest request, 
                        HttpServletResponse response) {
        // Lógica para obtener y mostrar datos
    }
    
    // Maneja solicitudes POST
    protected void doPost(HttpServletRequest request, 
                         HttpServletResponse response) {
        // Lógica para procesar formularios
    }
}
```

**Características:**
- ✅ Anotación @WebServlet para mapeo automático
- ✅ Método doGet para solicitudes GET
- ✅ Método doPost para solicitudes POST
- ✅ Instancia única de DAO
- ✅ Manejo de request/response

---

#### 🔹 Capa Utilidades

```java
public class DBConnection {
    
    // Propiedades de conexión
    private static final String URL = 
        "jdbc:mysql://localhost:3306/chinook";
    private static final String USER = "root";
    private static final String PASSWORD = "tu_contraseña";
    
    // Obtener conexión a base de datos
    public static Connection getConnection() 
            throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```

**Características:**
- ✅ Método estático para obtener conexiones
- ✅ Centralización de configuración
- ✅ Try-with-resources para cierre automático
- ✅ Manejo de excepciones SQL

---

# 7. FLUJO DE SOLICITUD

## 🔄 Ciclo Completo de una Solicitud HTTP

### Escenario: Usuario crea un nuevo artista

```
1. USUARIO INICIA
   └─→ Haz clic en "Añadir Artista"
       URL: http://localhost:8080/chinook/artist?action=new

2. NAVEGADOR ENVÍA GET
   └─→ GET /chinook/artist?action=new HTTP/1.1

3. SERVLET RECIBE (doGet)
   └─→ ArtistServlet detecta action="new"
   └─→ Carga formulario en blanco
   └─→ Redirige a add_artist.jsp

4. JSP SE RENDERIZA
   └─→ Formulario HTML se genera
   └─→ Usuario ve página con campos vacíos
   └─→ Campo: Name (nombre del artista)

5. USUARIO COMPLETA FORMULARIO
   └─→ Ingresa: "Led Zeppelin"
   └─→ Hace clic en "Guardar"

6. NAVEGADOR ENVÍA POST
   └─→ POST /chinook/artist HTTP/1.1
   └─→ Cuerpo:
       - action=add
       - name=Led Zeppelin

7. SERVLET RECIBE (doPost)
   └─→ ArtistServlet detecta action="add"
   └─→ Obtiene parámetro: name="Led Zeppelin"
   └─→ Crea instancia: new Artist("Led Zeppelin")
   └─→ Llama: artistDAO.addArtist("Led Zeppelin")

8. DAO ACCEDE A BASE DE DATOS
   └─→ Abre conexión con DBConnection.getConnection()
   └─→ Prepara SQL: INSERT INTO artists (Name) VALUES (?)
   └─→ Establece parámetro: "Led Zeppelin"
   └─→ Ejecuta: ps.executeUpdate()
   └─→ Cierra conexión

9. BASE DE DATOS PROCESA
   └─→ MySQL inserta el registro
   └─→ Genera nuevo ArtistId automáticamente
   └─→ Confirma éxito

10. SERVLET REDIRIGE
    └─→ response.sendRedirect("artist")
    └─→ El navegador hace GET a /chinook/artist

11. SERVLET CARGA LISTADO (doGet)
    └─→ ArtistServlet detecta action=null
    └─→ Obtiene lista: List<Artist> = artistDAO.getAllArtists()
    └─→ Establece atributo: request.setAttribute("artists", artists)
    └─→ Redirige a artists.jsp

12. JSP RENDERIZA LISTA
    └─→ <c:forEach items="${artists}" var="artist">
    └─→ Genera tabla HTML con todos los artistas
    └─→ "Led Zeppelin" aparece en la lista

13. NAVEGADOR MUESTRA RESULTADO
    └─→ Usuario ve la lista actualizada
    └─→ Verifica que "Led Zeppelin" está presente
    └─→ ¡Éxito! 🎉
```

### Diagrama de Secuencia

```
Usuario    Navegador    Servlet    DAO    MySQL
  │           │          │         │       │
  │──GET──────→│          │         │       │
  │           │──GET─────→│         │       │
  │           │          │─View───→│       │
  │           │←─JSP─────│         │       │
  │←──HTML────│          │         │       │
  │           │          │         │       │
  │──POST─────→│          │         │       │
  │           │──POST────→│         │       │
  │           │          │────INSERT────→ │
  │           │          │         │─INSERT→
  │           │          │         │←──OK──│
  │           │←──Redirect┤         │       │
  │←──303─────│          │         │       │
  │──GET──────→│          │         │       │
  │           │──GET─────→│         │       │
  │           │          │─SELECT──→│───→  │
  │           │          │         │←──Data
  │           │←──List───│         │       │
  │           │←──JSP────│         │       │
  │←──HTML────│          │         │       │
```

---

# 8. EJEMPLOS DE CÓDIGO

## 💻 Ejemplo Completo: CRUD de Artistas

### 1️⃣ Modelo (Artist.java)

```java
package model;

public class Artist {
    private int artistId;
    private String name;
    
    // Constructor vacío
    public Artist() {
    }
    
    // Constructor con parámetros
    public Artist(int artistId, String name) {
        this.artistId = artistId;
        this.name = name;
    }
    
    // Getters
    public int getArtistId() {
        return artistId;
    }
    
    public String getName() {
        return name;
    }
    
    // Setters
    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
```

### 2️⃣ DAO (ArtistDAO.java)

```java
package dao;

import model.Artist;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO {
    
    // Obtener todos los artistas
    public List<Artist> getAllArtists() {
        List<Artist> list = new ArrayList<>();
        
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT ArtistId, Name FROM artists";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Artist artist = new Artist();
                artist.setArtistId(rs.getInt("ArtistId"));
                artist.setName(rs.getString("Name"));
                list.add(artist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    // Obtener artista por ID
    public Artist getArtistById(int id) {
        Artist artist = null;
        
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT ArtistId, Name FROM artists WHERE ArtistId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                artist = new Artist();
                artist.setArtistId(rs.getInt("ArtistId"));
                artist.setName(rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return artist;
    }
    
    // Crear nuevo artista
    public void addArtist(String name) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO artists (Name) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Actualizar artista
    public void updateArtist(int id, String name) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "UPDATE artists SET Name = ? WHERE ArtistId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Eliminar artista
    public void deleteArtist(int id) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "DELETE FROM artists WHERE ArtistId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

### 3️⃣ Servlet (ArtistServlet.java)

```java
package servlet;

import dao.ArtistDAO;
import model.Artist;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/artist")
public class ArtistServlet extends HttpServlet {
    
    private ArtistDAO artistDAO = new ArtistDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, 
                        HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action == null) {
            // Listar todos
            List<Artist> artists = artistDAO.getAllArtists();
            request.setAttribute("artists", artists);
            request.getRequestDispatcher("/WEB-INF/views/artists.jsp")
                .forward(request, response);
        } 
        else if ("new".equals(action)) {
            // Formulario crear
            request.getRequestDispatcher("/WEB-INF/views/add_artist.jsp")
                .forward(request, response);
        } 
        else if ("edit".equals(action)) {
            // Formulario editar
            int id = Integer.parseInt(request.getParameter("id"));
            Artist artist = artistDAO.getArtistById(id);
            request.setAttribute("artist", artist);
            request.getRequestDispatcher("/WEB-INF/views/edit_artist.jsp")
                .forward(request, response);
        } 
        else if ("delete".equals(action)) {
            // Confirmación eliminar
            int id = Integer.parseInt(request.getParameter("id"));
            Artist artist = artistDAO.getArtistById(id);
            request.setAttribute("artist", artist);
            request.getRequestDispatcher("/WEB-INF/views/delete_artist.jsp")
                .forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, 
                         HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            // Crear
            String name = request.getParameter("name");
            artistDAO.addArtist(name);
        } 
        else if ("update".equals(action)) {
            // Actualizar
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            artistDAO.updateArtist(id, name);
        } 
        else if ("delete".equals(action)) {
            // Eliminar
            int id = Integer.parseInt(request.getParameter("id"));
            artistDAO.deleteArtist(id);
        }
        
        response.sendRedirect("artist");
    }
}
```

### 4️⃣ Vista (artists.jsp)

```jsp
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Artistas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/ystyle.css" 
          rel="stylesheet">
</head>
<body>
    
    <nav class="navbar">
        <span class="navbar-brand">Chinook</span>
    </nav>
    
    <div class="container mt-5">
        <h2 class="mb-4">Listado de Artistas</h2>
        
        <a href="artist?action=new" class="btn btn-success mb-3">
            Añadir Artista
        </a>
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${artists}" var="artist">
                    <tr>
                        <td>${artist.artistId}</td>
                        <td>${artist.name}</td>
                        <td>
                            <a href="artist?action=edit&id=${artist.artistId}" 
                               class="btn btn-warning btn-sm">
                                Editar
                            </a>
                            <a href="artist?action=delete&id=${artist.artistId}" 
                               class="btn btn-danger btn-sm">
                                Eliminar
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```

---

# 9. FUNCIONALIDADES PRINCIPALES

## ✨ Características Clave

### 🎯 CRUD Completo

#### CREATE (Crear)
```
Usuario clica "Añadir"
    ↓
Ve formulario vacío
    ↓
Completa campos
    ↓
Hace clic "Guardar"
    ↓
POST al Servlet
    ↓
Servlet llama DAO.insert()
    ↓
DAO ejecuta INSERT SQL
    ↓
Registro guardado en BD
    ↓
Redirige a listado
```

#### READ (Leer)
```
Solicitud GET al Servlet
    ↓
Servlet llama DAO.getAll()
    ↓
DAO ejecuta SELECT SQL
    ↓
ResultSet → List<Objeto>
    ↓
setAttribute en request
    ↓
Forward a JSP
    ↓
JSP itera con <c:forEach>
    ↓
Tabla HTML generada
```

#### UPDATE (Actualizar)
```
Usuario clica "Editar"
    ↓
GET con ?action=edit&id=1
    ↓
Servlet carga datos con DAO.getById()
    ↓
JSP rellena formulario
    ↓
Usuario modifica campos
    ↓
POST con action=update
    ↓
DAO ejecuta UPDATE SQL
    ↓
Redirige a listado
```

#### DELETE (Eliminar)
```
Usuario clica "Eliminar"
    ↓
Página de confirmación
    ↓
Usuario confirma
    ↓
POST con action=delete
    ↓
DAO ejecuta DELETE SQL
    ↓
Registro eliminado
    ↓
Redirige a listado
```

---

### 🔍 Búsqueda en Tiempo Real

```javascript
// search.js
document.getElementById('search').addEventListener('input', function() {
    let searchTerm = this.value.toLowerCase();
    let table = document.getElementById('table');
    let rows = table.getElementsByTagName('tr');
    
    for (let row of rows) {
        let text = row.textContent.toLowerCase();
        row.style.display = text.includes(searchTerm) ? '' : 'none';
    }
});
```

**Funcionamiento:**
- ✅ Busca mientras escribes (sin recargar página)
- ✅ Filtra todas las columnas
- ✅ Sensible a mayúsculas/minúsculas
- ✅ Instantáneo

---

### ↕️ Ordenamiento por Columnas

```javascript
// Función para ordenar
function sortTable(column, direction) {
    let rows = Array.from(table.rows).slice(1); // Omitir header
    
    rows.sort((a, b) => {
        let aVal = a.cells[column].textContent;
        let bVal = b.cells[column].textContent;
        
        if (direction === 'asc') {
            return aVal > bVal ? 1 : -1;
        } else {
            return aVal < bVal ? 1 : -1;
        }
    });
    
    // Reconstruir tabla
}
```

---

### 🎨 Interfaz Responsiva

```html
<!-- Bootstrap Grid -->
<div class="container">
    <div class="row">
        <div class="col-md-4">Card 1</div>
        <div class="col-md-4">Card 2</div>
        <div class="col-md-4">Card 3</div>
    </div>
</div>
```

**Características:**
- ✅ Se adapta a todos los tamaños
- ✅ Mobile-first design
- ✅ Media queries automáticas
- ✅ Bootstrap 5.3

---

# 10. INSTALACIÓN Y EJECUCIÓN

## 🚀 Pasos para Ejecutar el Proyecto

### 1. Requisitos Previos

```bash
# Verificar Java
java -version
# Debe mostrar Java 17 o superior

# Verificar Maven
mvn --version
# Debe mostrar Maven 3.6 o superior

# Verificar MySQL
mysql --version
# Debe mostrar MySQL 8.0 o superior
```

### 2. Base de Datos

```sql
-- Conectarse a MySQL
mysql -u root -p

-- Crear base de datos
CREATE DATABASE IF NOT EXISTS chinook;
USE chinook;

-- Crear tablas
CREATE TABLE artists (
    ArtistId INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(120)
);

CREATE TABLE genres (
    GenreId INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(120)
);

CREATE TABLE albums (
    AlbumId INT PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(160),
    ArtistId INT,
    FOREIGN KEY (ArtistId) REFERENCES artists(ArtistId)
);

CREATE TABLE playlists (
    PlaylistId INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(120)
);

-- Datos de ejemplo
INSERT INTO artists (Name) VALUES ('AC/DC');
INSERT INTO artists (Name) VALUES ('Accept');
INSERT INTO artists (Name) VALUES ('Aerosmith');

INSERT INTO genres (Name) VALUES ('Rock');
INSERT INTO genres (Name) VALUES ('Pop');
INSERT INTO genres (Name) VALUES ('Jazz');

INSERT INTO albums (Title, ArtistId) VALUES ('Back in Black', 1);
INSERT INTO albums (Title, ArtistId) VALUES ('Restless and Wild', 2);

INSERT INTO playlists (Name) VALUES ('Mi Playlist 1');
INSERT INTO playlists (Name) VALUES ('Mi Playlist 2');
```

### 3. Configuración

```java
// src/main/java/util/DBConnection.java
String url = "jdbc:mysql://localhost:3306/chinook";
String usuario = "root";
String contraseña = "tu_contraseña"; // Cambia esto
```

### 4. Compilación

```bash
cd d:\ECD\Programacion\chinook

# Compilar y crear WAR
mvn clean compile package -DskipTests

# El archivo chinook.war estará en target/
```

### 5. Desplegar en Tomcat

```bash
# Copiar WAR a Tomcat
copy target\chinook.war C:\tomcat\webapps\

# Iniciar Tomcat
C:\tomcat\bin\startup.bat

# Acceder a la aplicación
http://localhost:8080/chinook/
```

---

## 🎯 Puntos Clave de la Presentación

### Resumen Ejecutivo

1. **¿Qué es?** - Aplicación CRUD web para gestión musical
2. **¿Por qué?** - Demostrar patrón MVC en Java
3. **¿Cómo?** - Servlet, JSP, DAO, MySQL
4. **¿Resultado?** - Interfaz funcional y moderna

### Componentes Principales

| Componente | Función | Tecnología |
|-----------|---------|------------|
| **Frontend** | Presentación | HTML, CSS, Bootstrap, JS |
| **Backend** | Controladores | Servlets Jakarta 5.0 |
| **Modelos** | Datos | POJO Java |
| **DAO** | BD | JDBC, PreparedStatements |
| **Servidor** | Ejecución | Apache Tomcat 11 |
| **BD** | Almacenamiento | MySQL 8.0 |

### Flujo de Datos

```
Usuario → Navegador → Servlet → DAO → MySQL → (Datos) → JSP → Navegador → Usuario
```

### Conceptos Demorados

✅ Patrón MVC  
✅ Operaciones CRUD  
✅ Acceso a Base de Datos  
✅ Servlets y JSP  
✅ HTML/CSS/JavaScript  
✅ Arquitectura Web  
✅ Seguridad (PreparedStatements)  
✅ Responsive Design  

---

## 📝 Notas Adicionales para la Exposición

### Aspectos Técnicos a Destacar

1. **Seguridad**
   - PreparedStatements previenen SQL Injection
   - Validación de entrada
   - Manejo de excepciones

2. **Rendimiento**
   - Uso de índices en BD
   - Conexiones reutilizables
   - Try-with-resources para cierre automático

3. **Escalabilidad**
   - Patrón MVC facilita extensión
   - Fácil agregar nuevas entidades
   - Estructura modular

4. **Mantenibilidad**
   - Código limpio y comentado
   - Separación de responsabilidades
   - Convenciones de naming

### Demostración en Vivo

1. Mostrar página principal
2. Hacer clic en "Artistas"
3. Buscar un artista
4. Crear nuevo artista
5. Editar artista
6. Eliminar artista
7. Mostrar código fuente (DAO, Servlet, JSP)
8. Explicar flujo de datos

---

**¡Buena suerte en tu exposición! 🎤**

Este documento contiene todo lo que necesitas explicar sobre el proyecto.
