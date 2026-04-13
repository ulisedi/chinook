# 📚 Referencia Rápida de API - Chinook CRUD

Documento de referencia rápida para los endpoints y funcionalidades de la aplicación.

## 🌐 Endpoints Principales

### Páginas Públicas

| URL | Método | Descripción |
|-----|--------|-------------|
| `/chinook/` | GET | Página principal |
| `/chinook/index_crud.jsp` | GET | Página de inicio |

---

## 👥 Artistas (Artists)

### Listado de Artistas

**URL:** `/chinook/artist`  
**Método:** GET  
**Descripción:** Obtiene el listado de todos los artistas

**Respuesta:** Página con tabla de artistas

```html
<table class="table">
    <thead>
        <tr>
            <th>ID</th>
            <th>NOMBRE</th>
            <th>ACCIONES</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>1</td>
            <td>AC/DC</td>
            <td>
                <a href="artist?action=edit&id=1">Editar</a>
                <a href="artist?action=delete&id=1">Borrar</a>
            </td>
        </tr>
    </tbody>
</table>
```

---

### Ver Formulario de Crear Artista

**URL:** `/chinook/artist?action=new`  
**Método:** GET  
**Descripción:** Muestra el formulario para crear un nuevo artista

**Parámetros:**
- `action=new` - Modo crear

---

### Crear Nuevo Artista

**URL:** `/chinook/artist`  
**Método:** POST  
**Descripción:** Crea un nuevo artista

**Parámetros POST:**
```
action=add
name=Nombre del Artista
```

**Ejemplo:**
```html
<form method="post" action="artist">
    <input type="hidden" name="action" value="add">
    <input type="text" name="name" required>
    <button type="submit">Guardar</button>
</form>
```

**Respuesta:** Redirección a `/chinook/artist`

---

### Ver Formulario de Editar Artista

**URL:** `/chinook/artist?action=edit&id=1`  
**Método:** GET  
**Descripción:** Muestra el formulario para editar un artista

**Parámetros:**
- `action=edit` - Modo editar
- `id=1` - ID del artista a editar

---

### Actualizar Artista

**URL:** `/chinook/artist`  
**Método:** POST  
**Descripción:** Actualiza un artista existente

**Parámetros POST:**
```
action=update
id=1
name=Nuevo Nombre
```

**Respuesta:** Redirección a `/chinook/artist`

---

### Eliminar Artista

**URL:** `/chinook/artist?action=delete&id=1`  
**Método:** GET (confirmación de página)  
**Descripción:** Muestra página de confirmación de eliminación

**Parámetros:**
- `action=delete` - Modo eliminar
- `id=1` - ID del artista a eliminar

---

### Confirmar Eliminación de Artista

**URL:** `/chinook/artist`  
**Método:** POST  
**Descripción:** Confirma la eliminación del artista

**Parámetros POST:**
```
action=delete
id=1
```

**Respuesta:** Redirección a `/chinook/artist`

---

## 🎵 Álbumes (Albums)

### Listado de Álbumes

**URL:** `/chinook/album`  
**Método:** GET  
**Descripción:** Obtiene el listado de todos los álbumes

**Tabla mostrará:**
- AlbumId
- Title (Título del álbum)
- ArtistName (Nombre del artista)

---

### Ver Formulario de Crear Álbum

**URL:** `/chinook/album?action=new`  
**Método:** GET

---

### Crear Nuevo Álbum

**URL:** `/chinook/album`  
**Método:** POST

**Parámetros POST:**
```
action=add
title=Nombre del Álbum
artistId=1
```

---

### Ver Formulario de Editar Álbum

**URL:** `/chinook/album?action=edit&id=1`  
**Método:** GET

---

### Actualizar Álbum

**URL:** `/chinook/album`  
**Método:** POST

**Parámetros POST:**
```
action=update
id=1
title=Nuevo Título
artistId=2
```

---

### Eliminar Álbum

**URL:** `/chinook/album?action=delete&id=1`  
**Método:** GET / POST (confirmar)

---

## 🎭 Géneros (Genres)

### Listado de Géneros

**URL:** `/chinook/genre`  
**Método:** GET

**Tabla mostrará:**
- GenreId
- Name (Nombre del género)

---

### Crear Nuevo Género

**URL:** `/chinook/genre`  
**Método:** POST

**Parámetros POST:**
```
action=add
name=Nombre del Género
```

---

### Actualizar Género

**URL:** `/chinook/genre`  
**Método:** POST

**Parámetros POST:**
```
action=update
id=1
name=Nuevo Nombre
```

---

### Eliminar Género

**URL:** `/chinook/genre?action=delete&id=1`  
**Método:** GET / POST (confirmar)

---

## 🎙️ Playlists (Playlists)

### Listado de Playlists

**URL:** `/chinook/playlist`  
**Método:** GET

**Tabla mostrará:**
- PlaylistId
- Name (Nombre de la playlist)

---

### Crear Nueva Playlist

**URL:** `/chinook/playlist`  
**Método:** POST

**Parámetros POST:**
```
action=add
name=Nombre de la Playlist
```

---

### Actualizar Playlist

**URL:** `/chinook/playlist`  
**Método:** POST

**Parámetros POST:**
```
action=update
id=1
name=Nuevo Nombre
```

---

### Eliminar Playlist

**URL:** `/chinook/playlist?action=delete&id=1`  
**Método:** GET / POST (confirmar)

---

## 🔍 Funcionalidades de Cliente (JavaScript)

### Búsqueda (search.js)

```javascript
// Inicializar búsqueda
const searchManager = new SearchManager();
searchManager.init('search-input-id', 'table-body-id', 'sort-select-id');
```

**Métodos disponibles:**

```javascript
// Filtrar tabla por término de búsqueda
searchManager.filterTable(searchTerm);

// Ordenar columna
searchManager.sortByColumn('columnName', 'asc'); // o 'desc'

// Obtener índice de columna
const index = searchManager.getColumnIndex('columnName');
```

**Eventos:**

```javascript
// Al escribir en el buscador
document.getElementById('search-input').addEventListener('input', function() {
    // Se ejecuta la búsqueda automáticamente
});

// Al cambiar el selector de ordenamiento
document.getElementById('sort-select').addEventListener('change', function() {
    // Se aplica el nuevo ordenamiento
});
```

---

## 🎨 Clases CSS Disponibles

### Tabla

```html
<table class="table">
    <thead>
        <tr>
            <!-- Filas de encabezado -->
        </tr>
    </thead>
    <tbody>
        <!-- Filas del cuerpo (se colorean alternadamente) -->
        <!-- Filas impares: blanco (#ffffff) -->
        <!-- Filas pares: gris claro (#f0f0f0) -->
    </tbody>
</table>
```

### Botones

```html
<!-- Botón primario (azul) -->
<a href="#" class="btn btn-primary">Acción</a>

<!-- Botón de éxito (verde) -->
<a href="#" class="btn btn-success">Guardar</a>

<!-- Botón de advertencia (amarillo) -->
<a href="#" class="btn btn-warning">Editar</a>

<!-- Botón de peligro (rojo) -->
<a href="#" class="btn btn-danger">Eliminar</a>

<!-- Botón secundario (gris) -->
<a href="#" class="btn btn-secondary">Volver</a>
```

### Formularios

```html
<!-- Input de texto -->
<input type="text" class="form-control" name="name" required>

<!-- Select -->
<select class="form-select" name="artistId">
    <option value="">Selecciona...</option>
</select>

<!-- Textarea -->
<textarea class="form-control" name="description"></textarea>

<!-- Label -->
<label for="name" class="form-label">Nombre</label>
```

### Tarjetas (Cards)

```html
<div class="card">
    <div class="card-body">
        <h5 class="card-title">Título</h5>
        <p class="card-text">Descripción</p>
        <a href="#" class="btn btn-primary">Ir</a>
    </div>
</div>
```

### Alertas

```html
<!-- Alerta informativa -->
<div class="alert alert-info">Información</div>

<!-- Alerta de éxito -->
<div class="alert alert-success">Éxito</div>

<!-- Alerta de advertencia -->
<div class="alert alert-warning">Advertencia</div>

<!-- Alerta de error -->
<div class="alert alert-danger">Error</div>
```

---

## 🔌 Atributos de Datos (Data Attributes)

### Búsqueda

```html
<!-- Input de búsqueda -->
<input type="text" id="artist-search" placeholder="Buscar artista...">

<!-- Tabla de resultados -->
<table>
    <tbody id="artist-table-body">
        <!-- Filas se filtran dinámicamente -->
    </tbody>
</table>
```

### Ordenamiento

```html
<!-- Selector de ordenamiento -->
<select id="sort-select">
    <option value="">Ordenar por...</option>
    <option value="id-asc">ID (Ascendente)</option>
    <option value="id-desc">ID (Descendente)</option>
    <option value="nombre-asc">Nombre (A-Z)</option>
    <option value="nombre-desc">Nombre (Z-A)</option>
</select>
```

---

## 📱 Estados HTTP Comunes

| Código | Significado | Ejemplo |
|--------|------------|---------|
| 200 | OK | Solicitud exitosa |
| 302 | Found (Redirect) | Redirección después de guardar |
| 400 | Bad Request | Parámetros inválidos |
| 404 | Not Found | Recurso no encontrado |
| 500 | Server Error | Error en el servidor |

---

## 🗄️ Estructura de Respuesta de Datos

### Objeto Artist (JSON)

```json
{
    "id": 1,
    "name": "AC/DC"
}
```

### Objeto Album (JSON)

```json
{
    "id": 1,
    "title": "Back in Black",
    "artistId": 1,
    "artistName": "AC/DC"
}
```

### Objeto Genre (JSON)

```json
{
    "id": 1,
    "name": "Rock"
}
```

### Objeto Playlist (JSON)

```json
{
    "id": 1,
    "name": "Mi Playlist 1"
}
```

---

## 💡 Ejemplo de Flujo Completo

### 1. Usuario abre la aplicación

```
GET /chinook/ → index_crud.jsp
```

### 2. Usuario hace clic en "Artistas"

```
GET /chinook/artist → artists.jsp (lista todos)
```

### 3. Usuario busca "AC/DC"

```
JavaScript filtra la tabla en el navegador (sin solicitud al servidor)
```

### 4. Usuario hace clic en "Añadir Artista"

```
GET /chinook/artist?action=new → add_artist.jsp (formulario)
```

### 5. Usuario completa el formulario y hace clic en "Guardar"

```
POST /chinook/artist
  - action=add
  - name=Nuevo Artista
→ Servlet procesa y redirige
→ GET /chinook/artist (lista actualizada)
```

---

## 🔗 URL Completas de Ejemplo

```
Inicio:
http://localhost:8080/chinook/

Ver Artistas:
http://localhost:8080/chinook/artist

Crear Artista:
http://localhost:8080/chinook/artist?action=new

Editar Artista 1:
http://localhost:8080/chinook/artist?action=edit&id=1

Eliminar Artista 1:
http://localhost:8080/chinook/artist?action=delete&id=1

Ver Álbumes:
http://localhost:8080/chinook/album

Ver Géneros:
http://localhost:8080/chinook/genre

Ver Playlists:
http://localhost:8080/chinook/playlist
```

---

## 📊 Parámetros de Búsqueda y Ordenamiento

### Columnas Soportadas por Búsqueda

| Entidad | Columnas |
|---------|----------|
| **Artist** | id, nombre |
| **Album** | id, título, artista |
| **Genre** | id, nombre |
| **Playlist** | id, nombre |

### Direcciones de Ordenamiento

- `asc` - Ascendente (A-Z, 0-9)
- `desc` - Descendente (Z-A, 9-0)

---

## 🚨 Códigos de Error Comunes

| Error | Causa | Solución |
|-------|-------|----------|
| 404 Not Found | URL incorrecta | Verifica la URL |
| 500 Server Error | Error en la BD | Verifica conexión BD |
| 400 Bad Request | Parámetros faltantes | Revisa los parámetros POST |

---

**Versión:** 1.0 | Última actualización: 13 de abril de 2026
