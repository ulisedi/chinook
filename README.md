# 🎵 Chinook - Aplicación CRUD de Base de Datos Musical

Aplicación web interactiva para la gestión de una base de datos musical. Permite administrar artistas, géneros, álbumes y playlists con una interfaz moderna e intuitiva.

## 📋 Tabla de Contenidos

- [Características](#características)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Guía de Uso](#guía-de-uso)
- [Arquitectura](#arquitectura)
- [Base de Datos](#base-de-datos)
- [Desarrollo](#desarrollo)

---

## ✨ Características

### Funcionalidades Principales

✅ **Gestión de Artistas**
- Ver listado de artistas
- Crear nuevo artista
- Editar artista existente
- Eliminar artista

✅ **Gestión de Géneros**
- Ver listado de géneros
- Crear nuevo género
- Editar género existente
- Eliminar género

✅ **Gestión de Álbumes**
- Ver listado de álbumes con información del artista
- Crear nuevo álbum
- Editar álbum existente
- Eliminar álbum

✅ **Gestión de Playlists**
- Ver listado de playlists
- Crear nueva playlist
- Editar playlist existente
- Eliminar playlist

### Características de Interfaz

🎨 **Diseño Moderno**
- Interfaz inspirada en YouTube
- Tema claro único con paleta de colores profesional
- Responsive y adaptable a diferentes tamaños de pantalla

🔍 **Búsqueda y Ordenamiento**
- Búsqueda en tiempo real para todas las listas
- Ordenamiento por columnas (ASC/DESC)
- Filtrado instantáneo sin recargar la página

📱 **Navegación Intuitiva**
- Navbar consistente en todas las páginas
- Botones de acción claramente identificados
- Menú principal con acceso a todas las secciones

---

## 🔧 Requisitos

### Software Necesario

- **Java JDK 17** o superior
- **Apache Maven 3.6+** (para compilación)
- **Apache Tomcat 11.0.18** (o superior)
- **MySQL 8.0+** con la base de datos Chinook

### Dependencias de Proyecto

```xml
- Jakarta Servlet API 5.0.0
- Jakarta JSP API 3.0.0
- Jakarta JSTL 3.0.1
- MySQL Connector J 8.3.0
```

---

## 📦 Instalación

### 1. Clonar o Descargar el Proyecto

```bash
cd d:\ECD\Programacion\chinook
```

### 2. Configurar la Base de Datos

Asegúrate de que tienes la base de datos Chinook instalada en MySQL:

```sql
-- Si no tienes la base de datos, créala con:
CREATE DATABASE IF NOT EXISTS chinook;
```

### 3. Configurar la Conexión a la Base de Datos

Edita el archivo `src/main/java/util/DBConnection.java` con tus credenciales:

```java
String url = "jdbc:mysql://localhost:3306/chinook";
String usuario = "root";
String contraseña = "tu_contraseña";
```

### 4. Compilar el Proyecto

```bash
mvn clean compile package -DskipTests
```

Esto generará un archivo `chinook.war` en la carpeta `target/`

### 5. Desplegar en Tomcat

```bash
# Copiar el WAR a la carpeta webapps de Tomcat
copy target\chinook.war %CATALINA_HOME%\webapps\

# Iniciar Tomcat
%CATALINA_HOME%\bin\startup.bat
```

### 6. Acceder a la Aplicación

Abre tu navegador y ve a:
```
http://localhost:8080/chinook/
```

---

## 📂 Estructura del Proyecto

```
chinook/
├── src/
│   └── main/
│       ├── java/
│       │   ├── dao/
│       │   │   ├── ArtistDAO.java
│       │   │   ├── AlbumDAO.java
│       │   │   ├── GenreDAO.java
│       │   │   └── PlaylistDAO.java
│       │   ├── model/
│       │   │   ├── Artist.java
│       │   │   ├── Album.java
│       │   │   ├── Genre.java
│       │   │   └── Playlist.java
│       │   ├── servlet/
│       │   │   ├── ArtistServlet.java
│       │   │   ├── AlbumServlet.java
│       │   │   ├── GenreServlet.java
│       │   │   ├── PlaylistServlet.java
│       │   │   ├── HomeServlet.java
│       │   │   └── ErrorServlet.java
│       │   └── util/
│       │       └── DBConnection.java
│       └── webapp/
│           ├── index_crud.jsp (Página principal)
│           ├── WEB-INF/
│           │   ├── head/
│           │   │   └── head.jsp (Cabecera común)
│           │   └── views/
│           │       ├── artists.jsp
│           │       ├── albums.jsp
│           │       ├── genres.jsp
│           │       ├── playlists.jsp
│           │       ├── add_*.jsp (Formularios de creación)
│           │       ├── edit_*.jsp (Formularios de edición)
│           │       ├── update_*.jsp (Formularios de actualización)
│           │       └── delete_*.jsp (Confirmación de eliminar)
│           ├── css/
│           │   ├── ystyle.css (Estilos principales)
│           │   ├── index.css (Estilos del inicio)
│           │   ├── bootstrap.min.css (Bootstrap)
│           │   └── ... (otros archivos CSS)
│           ├── js/
│           │   ├── search.js (Búsqueda y ordenamiento)
│           │   ├── bootstrap.bundle.min.js
│           │   └── ... (otros archivos JS)
│           └── img/
│               └── logo.ico
├── pom.xml (Configuración de Maven)
└── README.md (Este archivo)
```

---

## 🛠️ Tecnologías Utilizadas

### Backend

| Tecnología | Versión | Uso |
|-----------|---------|-----|
| **Java** | 17 | Lenguaje principal |
| **Jakarta Servlet** | 5.0.0 | API de Servlets |
| **Jakarta JSP** | 3.0.0 | Plantillas dinámicas |
| **MySQL** | 8.3.0 | Base de datos |
| **Maven** | 3.6+ | Gestor de dependencias |

### Frontend

| Tecnología | Versión | Uso |
|-----------|---------|-----|
| **Bootstrap** | 5.3.2 | Framework CSS |
| **Font Awesome** | 6.4.0 | Iconos |
| **JavaScript** | ES6+ | Interactividad |
| **HTML5** | - | Estructura |
| **CSS3** | - | Estilos |

### Servidor de Aplicaciones

| Software | Versión | Uso |
|---------|---------|-----|
| **Apache Tomcat** | 11.0.18 | Servidor de aplicaciones |

---

## 📚 Guía de Uso

### Página Principal

La página principal (`index_crud.jsp`) muestra 4 secciones principales:

1. **Artistas** - Gestión de artistas
2. **Géneros** - Gestión de géneros
3. **Álbumes** - Gestión de álbumes
4. **Playlists** - Gestión de playlists

Haz clic en cualquiera de los botones para acceder a la sección correspondiente.

### Operaciones CRUD

#### Ver Listado
- Cada sección muestra un listado de items
- Los items se muestran en una tabla con alternancia de colores (blanco y gris)
- Las columnas varían según el tipo de item

#### Buscar
- Usa el cuadro de búsqueda para filtrar items en tiempo real
- La búsqueda se realiza sobre el nombre/título del item

#### Ordenar
- Usa el menú desplegable de ordenamiento
- Selecciona columna y dirección (ASC/DESC)
- Los resultados se actualizan al instante

#### Crear (Crear Nuevo Item)
- Haz clic en el botón "Añadir [Item]"
- Completa el formulario con la información
- Haz clic en "Guardar"

#### Editar
- Haz clic en el botón "Editar" en la fila correspondiente
- Modifica los campos deseados
- Haz clic en "Actualizar"

#### Eliminar
- Haz clic en el botón "Borrar" en la fila correspondiente
- Confirma la eliminación
- El item será eliminado de la base de datos

---

## 🏗️ Arquitectura

### Patrón MVC (Model-View-Controller)

La aplicación sigue el patrón MVC:

```
┌─────────────────┐
│   VIEW (JSP)    │ ← Presenta los datos al usuario
└────────┬────────┘
         │
         ↓
┌─────────────────┐
│   SERVLET       │ ← Controla el flujo de la aplicación
│   (Controller)  │
└────────┬────────┘
         │
         ↓
┌─────────────────┐
│   DAO + MODEL   │ ← Gestiona los datos (BD)
│ (Model + Data)  │
└─────────────────┘
```

### Flujo de Solicitud

1. **Usuario** → Hace una solicitud (click en botón)
2. **Servlet** → Recibe la solicitud y procesa la lógica
3. **DAO** → Accede a la base de datos
4. **Model** → Representa los datos
5. **JSP** → Renderiza la vista con los datos
6. **Navegador** → Muestra la página al usuario

---

## 🗄️ Base de Datos

### Tablas Principales

#### artists
```
┌──────────────┬──────────────────┐
│ Columna      │ Tipo             │
├──────────────┼──────────────────┤
│ ArtistId     │ INT (PK)         │
│ Name         │ VARCHAR(120)     │
└──────────────┴──────────────────┘
```

#### genres
```
┌──────────────┬──────────────────┐
│ Columna      │ Tipo             │
├──────────────┼──────────────────┤
│ GenreId      │ INT (PK)         │
│ Name         │ VARCHAR(120)     │
└──────────────┴──────────────────┘
```

#### albums
```
┌──────────────┬──────────────────┐
│ Columna      │ Tipo             │
├──────────────┼──────────────────┤
│ AlbumId      │ INT (PK)         │
│ Title        │ VARCHAR(160)     │
│ ArtistId     │ INT (FK)         │
└──────────────┴──────────────────┘
```

#### playlists
```
┌──────────────┬──────────────────┐
│ Columna      │ Tipo             │
├──────────────┼──────────────────┤
│ PlaylistId   │ INT (PK)         │
│ Name         │ VARCHAR(120)     │
└──────────────┴──────────────────┘
```

### Relaciones

```
artists (1) ─────→ (N) albums
genres  (1) ─────→ (N) artists (indirectamente)
playlists ────────→ tracks (relación N:N)
```

---

## 👨‍💻 Desarrollo

### Compilación

```bash
# Compilación limpia
mvn clean compile

# Compilación con tests
mvn clean compile package

# Compilación sin tests
mvn clean compile package -DskipTests
```

### Ejecución Local

Para probar durante el desarrollo:

```bash
# Opción 1: Usando Tomcat Maven Plugin
mvn tomcat7:run

# Opción 2: Desplegar el WAR en Tomcat manualmente
mvn clean package -DskipTests
# Luego copiar chinook.war a %CATALINA_HOME%\webapps\
```

### Estructura de Carpetas de Código

#### `src/main/java/dao/`
Contiene las clases DAO (Data Access Object) para acceder a la base de datos:
- `ArtistDAO.java` - Operaciones CRUD para artistas
- `AlbumDAO.java` - Operaciones CRUD para álbumes
- `GenreDAO.java` - Operaciones CRUD para géneros
- `PlaylistDAO.java` - Operaciones CRUD para playlists

#### `src/main/java/model/`
Contiene los modelos (entidades) que representan los datos:
- `Artist.java` - Clase modelo para artista
- `Album.java` - Clase modelo para álbum
- `Genre.java` - Clase modelo para género
- `Playlist.java` - Clase modelo para playlist

#### `src/main/java/servlet/`
Contiene los servlets que manejan las solicitudes HTTP:
- `ArtistServlet.java` - Controlador para gestión de artistas
- `AlbumServlet.java` - Controlador para gestión de álbumes
- `GenreServlet.java` - Controlador para gestión de géneros
- `PlaylistServlet.java` - Controlador para gestión de playlists
- `HomeServlet.java` - Controlador para la página principal

#### `src/main/java/util/`
Contiene clases utilitarias:
- `DBConnection.java` - Gestión de conexiones a base de datos

#### `src/main/webapp/WEB-INF/views/`
Contiene las vistas JSP:
- `artists.jsp` - Listado de artistas
- `albums.jsp` - Listado de álbumes
- `genres.jsp` - Listado de géneros
- `playlists.jsp` - Listado de playlists
- `add_*.jsp` - Formularios para crear nuevos items
- `edit_*.jsp` - Formularios para editar items
- `update_*.jsp` - Formularios de actualización
- `delete_*.jsp` - Páginas de confirmación de eliminación

---

## 🎨 Estilos y Personalización

### Archivos CSS Principales

#### `ystyle.css` (920 líneas)
Estilos principales de la aplicación:
- Tema claro con variables CSS
- Estilos de navbar y navegación
- Estilos de tablas con colores alternados
- Estilos de formularios
- Estilos de tarjetas (cards)
- Responsive design

#### `index.css`
Estilos específicos para la página principal:
- Layout de cards en grid
- Centrado de botones
- Estilos de títulos de sección

### Colores Utilizados

```css
/* Tema Claro (Único) */
--yt-bg-color: #ffffff        /* Fondo blanco */
--yt-text-color: #030303      /* Texto oscuro */
--yt-secondary-color: #f9f9f9 /* Fondo secundario */
--yt-border-color: #e0e0e0    /* Bordes */

/* Tabla */
--table-row-odd: #ffffff      /* Filas impares: blanco */
--table-row-even: #f0f0f0     /* Filas pares: gris claro */
```

### Fuentes

- **Roboto** - Fuente principal desde Google Fonts
- **Font Awesome 6.4.0** - Iconos

---

## 🐛 Solución de Problemas

### Problema: "No se puede conectar a la base de datos"

**Solución:**
1. Verifica que MySQL está ejecutándose
2. Comprueba las credenciales en `DBConnection.java`
3. Asegúrate de que la base de datos Chinook existe

### Problema: "404 - Página no encontrada"

**Solución:**
1. Verifica que el contexto es `/chinook`
2. Comprueba que el WAR está desplegado en Tomcat
3. Reinicia Tomcat

### Problema: "Error de compilación Maven"

**Solución:**
```bash
# Limpiar caché de Maven
mvn clean

# Descargar dependencias nuevamente
mvn dependency:resolve

# Recompilar
mvn compile package -DskipTests
```

### Problema: "Estilos no se cargan"

**Solución:**
1. Abre las herramientas de desarrollador (F12)
2. Ve a la pestaña de red
3. Busca los archivos CSS
4. Si devuelven 404, reinicia Tomcat
5. Limpia el caché del navegador (Ctrl+Shift+Delete)

---

## 📖 Documentación Adicional

### Comentarios en el Código

El código contiene comentarios en español explicando:
- Propósito de cada clase
- Métodos principales
- Lógica compleja

### Ejemplo de Consulta DAO

```java
public List<Artist> getAll() {
    List<Artist> artists = new ArrayList<>();
    String sql = "SELECT ArtistId, Name FROM artists";
    
    try (Connection conn = DBConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            Artist artist = new Artist(
                rs.getInt("ArtistId"),
                rs.getString("Name")
            );
            artists.add(artist);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return artists;
}
```

---

## 📋 Checklist de Configuración

- [ ] Java JDK 17 instalado
- [ ] Maven configurado
- [ ] MySQL 8.0+ con base de datos Chinook
- [ ] Credenciales de BD en `DBConnection.java`
- [ ] Tomcat 11.0.18+ instalado
- [ ] Proyecto compilado con `mvn clean compile package -DskipTests`
- [ ] WAR desplegado en Tomcat
- [ ] Tomcat ejecutándose
- [ ] Aplicación accesible en `http://localhost:8080/chinook/`

---

## 📝 Historial de Cambios

### Versión 1.0 (Actual)
- ✅ Implementación completa de CRUD para Artistas, Géneros, Álbumes y Playlists
- ✅ Interfaz responsiva inspirada en YouTube
- ✅ Búsqueda en tiempo real
- ✅ Ordenamiento por columnas
- ✅ Tema claro único
- ✅ Documentación completa

---

## 👤 Autor

**Proyecto:** Chinook CRUD  
**Versión:** 1.0-SNAPSHOT  
**Última actualización:** 13 de abril de 2026

---

## 📄 Licencia

Este proyecto es de uso educativo.

---

## 📞 Soporte

Para reportar problemas o sugerencias, verifica:
1. Que la base de datos está correctamente configurada
2. Que Tomcat está ejecutándose
3. Los logs de Tomcat en `%CATALINA_HOME%\logs\`

---

**¡Gracias por usar Chinook CRUD! 🎵**
