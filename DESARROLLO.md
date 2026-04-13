# 🚀 Guía de Desarrollo - Chinook CRUD

Documento detallado para desarrolladores que deseen contribuir o extender la aplicación Chinook.

## 📑 Contenidos

1. [Setup de Desarrollo](#setup-de-desarrollo)
2. [Estructura de Código](#estructura-de-código)
3. [Convenciones de Código](#convenciones-de-código)
4. [Flujo de Trabajo](#flujo-de-trabajo)
5. [Agregar Nueva Funcionalidad](#agregar-nueva-funcionalidad)
6. [Testing](#testing)
7. [Debugging](#debugging)

---

## 🛠️ Setup de Desarrollo

### Requisitos
- Java JDK 17+
- Maven 3.6+
- IDE: IntelliJ IDEA, Eclipse o VS Code
- Git
- MySQL 8.0+

### Pasos Iniciales

```bash
# 1. Clonar o descargar el repositorio
cd d:\ECD\Programacion\chinook

# 2. Verificar que Maven está instalado
mvn --version

# 3. Instalar dependencias
mvn install

# 4. Compilar el proyecto
mvn clean compile

# 5. Ejecutar en Tomcat (desarrollo)
mvn tomcat7:run
```

La aplicación estará disponible en: `http://localhost:8080/chinook/`

---

## 📂 Estructura de Código

### Patrón DAO (Data Access Object)

Cada entidad tiene su correspondiente DAO:

```java
// Ejemplo: ArtistDAO.java
public class ArtistDAO {
    
    // Obtener todos los artistas
    public List<Artist> getAll() { }
    
    // Obtener artista por ID
    public Artist getById(int id) { }
    
    // Insertar nuevo artista
    public boolean insert(Artist artist) { }
    
    // Actualizar artista existente
    public boolean update(Artist artist) { }
    
    // Eliminar artista
    public boolean delete(int id) { }
}
```

### Patrón Servlet

Los servlets manejan las solicitudes HTTP:

```java
// Ejemplo: ArtistServlet.java
public class ArtistServlet extends HttpServlet {
    
    private ArtistDAO artistDAO = new ArtistDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, 
                        HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("new".equals(action)) {
            // Mostrar formulario de crear
            request.getRequestDispatcher("/WEB-INF/views/add_artist.jsp")
                .forward(request, response);
        } else if ("edit".equals(action)) {
            // Editar artista existente
            int id = Integer.parseInt(request.getParameter("id"));
            Artist artist = artistDAO.getById(id);
            request.setAttribute("artist", artist);
            request.getRequestDispatcher("/WEB-INF/views/edit_artist.jsp")
                .forward(request, response);
        } else {
            // Listar todos los artistas
            List<Artist> artists = artistDAO.getAll();
            request.setAttribute("artists", artists);
            request.getRequestDispatcher("/WEB-INF/views/artists.jsp")
                .forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, 
                         HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            // Crear nuevo artista
            String name = request.getParameter("name");
            Artist artist = new Artist(0, name);
            artistDAO.insert(artist);
        } else if ("update".equals(action)) {
            // Actualizar artista
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            Artist artist = new Artist(id, name);
            artistDAO.update(artist);
        } else if ("delete".equals(action)) {
            // Eliminar artista
            int id = Integer.parseInt(request.getParameter("id"));
            artistDAO.delete(id);
        }
        
        response.sendRedirect("artist");
    }
}
```

---

## 📏 Convenciones de Código

### Nombres

| Elemento          | Patrón            | Ejemplo |
|----------         |--------           |---------|
| **Clases**        | PascalCase        | `ArtistDAO` |
| **Métodos**       | camelCase         | `getArtistById()` |
| **Variables**     | camelCase         | `artistName` |
| **Constantes**    | UPPER_SNAKE_CASE  | `MAX_ARTISTS = 1000` |
| **Archivos JSP**  | snake_case        | `add_artist.jsp` |

### Comentarios

```java
// Comentario de una línea
public void method() {
    // Código con comentario
}

/**
 * Documentación de método
 * @param id ID del artista
 * @return Artista encontrado o null
 */
public Artist getById(int id) {
    // Implementación
}
```

### Formato de Código

```java
// Indentación: 4 espacios
if (condition) {
    // Código
}

// Llaves en la misma línea
public void method() {
    // Código
}

// Espacios alrededor de operadores
int result = a + b;
```

---

## 🔄 Flujo de Trabajo

### Desarrollo de Nueva Funcionalidad

1. **Crear la rama**
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```

2. **Implementar en orden:**
   - Modelo (model/)
   - DAO (dao/)
   - Servlet (servlet/)
   - Vistas (webapp/WEB-INF/views/)

3. **Compilar y probar**
   ```bash
   mvn clean compile package -DskipTests
   ```

4. **Commit y Push**
   ```bash
   git add .
   git commit -m "feat: agregar nueva funcionalidad"
   git push origin feature/nueva-funcionalidad
   ```

---

## ➕ Agregar Nueva Funcionalidad

### Ejemplo: Agregar Categorías

#### 1. Crear el Modelo

```java
// src/main/java/model/Category.java
public class Category {
    private int id;
    private String name;
    
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```

#### 2. Crear el DAO

```java
// src/main/java/dao/CategoryDAO.java
public class CategoryDAO {
    
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT CategoryId, Name FROM categories";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Category category = new Category(
                    rs.getInt("CategoryId"),
                    rs.getString("Name")
                );
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return categories;
    }
    
    public Category getById(int id) {
        String sql = "SELECT CategoryId, Name FROM categories WHERE CategoryId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Category(
                    rs.getInt("CategoryId"),
                    rs.getString("Name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public boolean insert(Category category) {
        String sql = "INSERT INTO categories (Name) VALUES (?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, category.getName());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean update(Category category) {
        String sql = "UPDATE categories SET Name = ? WHERE CategoryId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean delete(int id) {
        String sql = "DELETE FROM categories WHERE CategoryId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
```

#### 3. Crear el Servlet

```java
// src/main/java/servlet/CategoryServlet.java
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
    
    private CategoryDAO categoryDAO = new CategoryDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, 
                        HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("new".equals(action)) {
            request.getRequestDispatcher(
                "/WEB-INF/views/add_category.jsp")
                .forward(request, response);
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Category category = categoryDAO.getById(id);
            request.setAttribute("category", category);
            request.getRequestDispatcher(
                "/WEB-INF/views/edit_category.jsp")
                .forward(request, response);
        } else {
            List<Category> categories = categoryDAO.getAll();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher(
                "/WEB-INF/views/categories.jsp")
                .forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, 
                         HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            String name = request.getParameter("name");
            Category category = new Category(0, name);
            categoryDAO.insert(category);
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            Category category = new Category(id, name);
            categoryDAO.update(category);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            categoryDAO.delete(id);
        }
        
        response.sendRedirect("category");
    }
}
```

#### 4. Crear las Vistas JSP

```jsp
<!-- src/main/webapp/WEB-INF/views/categories.jsp -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Listado de Categorías</title>
    <%@ include file="/WEB-INF/head/head.jsp" %>
</head>
<body class="bg-light">

<nav class="navbar">
    <a href="${pageContext.request.contextPath}/index_crud.jsp" 
       class="navbar-brand-link">
        <img src="${pageContext.request.contextPath}/img/logo.ico" 
             alt="Logo" class="navbar-logo">
        <span class="navbar-brand">Chinook</span>
    </a>
</nav>

<div class="container mt-5">
    <h2 class="mb-4">
        Listado de <span style="color: #00aae4;">Categorías</span>
    </h2>
    
    <div class="mb-3">
        <a href="index_crud.jsp" class="btn btn-secondary">
            Volver al menú
        </a>
        <a href="category?action=new" class="btn btn-success">
            Añadir Categoría
        </a>
    </div>
    
    <input type="text" id="category-search" placeholder="Buscar..." 
           class="form-control mb-3">
    
    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody id="category-table-body">
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.id}</td>
                    <td>${category.name}</td>
                    <td>
                        <a href="category?action=edit&id=${category.id}" 
                           class="btn btn-warning btn-sm">Editar</a>
                        <a href="category?action=delete&id=${category.id}" 
                           class="btn btn-danger btn-sm">Borrar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script src="${pageContext.request.contextPath}/js/search.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const searchManager = new SearchManager();
        searchManager.init('category-search', 'category-table-body');
    });
</script>

</body>
</html>
```

---

## 🧪 Testing

### Pruebas Manuales

```bash
# 1. Compilar sin ejecutar tests
mvn clean compile package -DskipTests

# 2. Desplegar en Tomcat

# 3. Probar manualmente cada funcionalidad:
# - Ver listados
# - Crear nuevo item
# - Editar item
# - Eliminar item
# - Búsqueda
# - Ordenamiento
```

### Verificar Base de Datos

```sql
-- Conectarse a MySQL
mysql -u root -p

-- Usar base de datos Chinook
USE chinook;

-- Ver tabla de artistas
SELECT * FROM artists LIMIT 10;

-- Contar registros
SELECT COUNT(*) FROM artists;
```

---

## 🐛 Debugging

### Usando Tomcat Logs

```bash
# Ver logs en tiempo real (Windows)
type %CATALINA_HOME%\logs\catalina.out

# O en PowerShell
Get-Content $env:CATALINA_HOME\logs\catalina.out -Tail 50 -Wait
```

### Debug en IDE

#### IntelliJ IDEA

1. Agregar punto de ruptura (click en el número de línea)
2. Ir a Run → Debug 'Tomcat'
3. El código se detendrá en el punto de ruptura

#### Eclipse

1. Configurar servidor de depuración
2. Agregar puntos de ruptura
3. Run → Debug As → Debug on Server

### Sentencias de Debug

```java
// Imprimir en consola
System.out.println("Debug: " + variable);

// Imprimir en logs de Tomcat
System.err.println("Error: " + e.getMessage());

// Stack trace
e.printStackTrace();
```

### Verificar Variables

```java
// En JSP
<p>Debug: ${artist}</p>

// En servlet
System.out.println("Artist: " + artist.getName());
```

---

## 📦 Empaquetado

### Generar WAR

```bash
# Compilar y empaquetar
mvn clean package -DskipTests

# El WAR estará en: target/chinook.war
```

### Desplegar en Producción

```bash
# Parar Tomcat
%CATALINA_HOME%\bin\shutdown.bat

# Copiar WAR
copy target\chinook.war %CATALINA_HOME%\webapps\

# Iniciar Tomcat
%CATALINA_HOME%\bin\startup.bat
```

---

## 📚 Recursos Útiles

### Documentación Oficial
- [Jakarta Servlet API](https://jakarta.ee/specifications/servlet/)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Bootstrap 5](https://getbootstrap.com/docs/5.3/)

### Tutoriales
- JDBC y PreparedStatements
- JSP Best Practices
- MVC Pattern en Java Web

---

## ✅ Checklist antes de Commit

- [ ] Código compilado sin errores
- [ ] Funcionalidad probada manualmente
- [ ] Nombres de variables consistentes
- [ ] Comentarios en código complejo
- [ ] Sin código comentado innecesario
- [ ] Sin datos sensibles en código
- [ ] JSP con HTML válido
- [ ] CSS sin conflictos de estilos

---

**¡Happy coding! 🚀**
