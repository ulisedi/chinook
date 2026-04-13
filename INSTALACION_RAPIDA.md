# 🚀 Guía de Instalación Rápida - Chinook CRUD

Pasos sencillos para tener la aplicación ejecutándose en tu máquina.

## ⏱️ Tiempo Estimado: 15 minutos

---

## 1️⃣ Verificar Requisitos

### ✅ Verificar Java

```bash
java -version
```

**Esperado:** Java 17 o superior

```
openjdk version "17.0.x" ...
```

### ✅ Verificar Maven

```bash
mvn --version
```

**Esperado:** Maven 3.6 o superior

```
Apache Maven 3.8.x ...
```

### ✅ Verificar MySQL

```bash
mysql --version
```

**Esperado:** MySQL 8.0 o superior

```
mysql Ver 8.0.x for Win64 ...
```

> **Nota:** Si alguno no está instalado, descárgalos desde:
> - Java: [java.com](https://www.java.com/)
> - Maven: [maven.apache.org](https://maven.apache.org/)
> - MySQL: [mysql.com](https://www.mysql.com/)

---

## 2️⃣ Preparar Base de Datos

### Inicia MySQL

```bash
# Windows (si está registrado como servicio)
net start MySQL80

# O abre MySQL Command Line Client
```

### Crea la Base de Datos

```sql
-- Conectarse como root
mysql -u root -p

-- Pega los siguientes comandos:
CREATE DATABASE IF NOT EXISTS chinook;

USE chinook;

-- Tablas básicas
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

---

## 3️⃣ Configurar Credenciales de Base de Datos

Abre el archivo:
```
src\main\java\util\DBConnection.java
```

Busca esta sección:

```java
String url = "jdbc:mysql://localhost:3306/chinook";
String usuario = "root";
String contraseña = "tu_contraseña";
```

**Cambia `tu_contraseña` por tu contraseña de MySQL.**

> Ejemplo:
> ```java
> String contraseña = "admin123";
> ```

---

## 4️⃣ Compilar el Proyecto

Abre CMD o PowerShell en la carpeta del proyecto:

```bash
# Navega a la carpeta del proyecto
cd d:\ECD\Programacion\chinook

# Compilar y crear WAR
mvn clean compile package -DskipTests
```

**Esperado:** 
```
[INFO] BUILD SUCCESS
[INFO] Total time: XX.XXX s
```

El archivo `chinook.war` se encontrará en `target/`

---

## 5️⃣ Instalar Tomcat (si no lo tienes)

### Descarga Tomcat 11

1. Descarga desde: [tomcat.apache.org](https://tomcat.apache.org/)
2. Versión recomendada: **Apache Tomcat 11.0.18**
3. Extrae la carpeta (ejemplo: `C:\tomcat`)

### Variables de Entorno (Opcional pero recomendado)

**Windows:**

1. Click derecho en **Este equipo** → **Propiedades**
2. **Variables de entorno** → **Nueva variable de sistema:**
   - Nombre: `CATALINA_HOME`
   - Valor: `C:\tomcat` (ajusta la ruta según tu instalación)
3. Click en **Aceptar**

---

## 6️⃣ Desplegar en Tomcat

### Opción A: Manual (Recomendado)

```bash
# Copiar el WAR a Tomcat
copy target\chinook.war C:\tomcat\webapps\

# O usando PowerShell
Copy-Item target\chinook.war C:\tomcat\webapps\
```

### Opción B: Automática (Maven Plugin)

```bash
mvn tomcat7:deploy
```

---

## 7️⃣ Iniciar Tomcat

### Windows - Usar Startup Script

```bash
# Navega a la carpeta bin de Tomcat
cd C:\tomcat\bin

# Ejecuta startup.bat
startup.bat

# O en PowerShell
.\startup.bat
```

### Verificar que Tomcat inició correctamente

```bash
# Abre navegador y ve a:
http://localhost:8080/manager/html

# Deberías ver la página de Tomcat
```

---

## 8️⃣ Acceder a la Aplicación

Abre tu navegador favorito y ve a:

```
http://localhost:8080/chinook/
```

¡Si ves la página de inicio de Chinook, ¡**LISTO**! 🎉

---

## 🧪 Prueba Rápida

1. **Haz clic en "Artistas"** → Deberías ver: AC/DC, Accept, Aerosmith
2. **Busca "AC/DC"** → Deberías ver solo ese artista
3. **Haz clic en "Añadir Artista"** → Completa el formulario
4. **Haz clic en "Guardar"** → Regresa al listado
5. **Verifica que el nuevo artista aparece**

✅ Si todo funciona, ¡la aplicación está configurada correctamente!

---

## ❌ Soluciona Problemas

### Error: "No se puede conectar a la BD"

```
Conexión a servidor de base de datos rechazada
```

**Soluciones:**
1. Verifica que MySQL está ejecutándose:
   ```bash
   net start MySQL80
   ```
2. Verifica las credenciales en `DBConnection.java`
3. Asegúrate que la base de datos `chinook` existe:
   ```sql
   SHOW DATABASES;
   ```

### Error: "404 - Página no encontrada"

```
HTTP Status 404 – Not Found
```

**Soluciones:**
1. Verifica que estás usando `http://localhost:8080/chinook/`
2. Verifica que `chinook.war` está en `%CATALINA_HOME%\webapps\`
3. Reinicia Tomcat

### Error: "8080 ya está en uso"

```
Address already in use: bind
```

**Soluciones:**
```bash
# Encuentra qué está usando el puerto
netstat -ano | findstr :8080

# O cambia el puerto de Tomcat en:
# %CATALINA_HOME%\conf\server.xml
# Busca <Connector port="8080"... y cambia a otro puerto
```

### Los estilos no cargan

```
Archivos CSS no se visualizan
```

**Soluciones:**
1. Limpia el caché del navegador: `Ctrl + Shift + Delete`
2. Abre herramientas de desarrollador: `F12`
3. Verifica pestaña **Network** para ver errores 404
4. Reinicia Tomcat

---

## 🔒 Detener la Aplicación

### Parar Tomcat

```bash
# Navega a bin
cd C:\tomcat\bin

# Ejecuta shutdown.bat
shutdown.bat

# O en PowerShell
.\shutdown.bat
```

---

## 📝 Notas Importantes

- **Credenciales por defecto:** Usuario: `root`, Contraseña: *tu_contraseña*
- **Puerto por defecto:** 8080
- **Contexto de la aplicación:** `/chinook/`
- **Logs de Tomcat:** `%CATALINA_HOME%\logs\catalina.out`

---

## 📞 Necesitas Ayuda?

1. Revisa los **logs de Tomcat**: `%CATALINA_HOME%\logs\catalina.out`
2. Abre **Herramientas de Desarrollador** (F12) y mira la consola
3. Asegúrate que **MySQL está ejecutándose**
4. Verifica que las **credenciales de BD son correctas**

---

**¡Disfruta usando Chinook CRUD! 🎵**

Versión: 1.0 | Última actualización: 13 de abril de 2026
