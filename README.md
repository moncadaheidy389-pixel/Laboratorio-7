# PetMatch Mini – Laboratorio 7

Aplicación web de ejemplo que muestra un **catálogo de mascotas**. Está construida con **Java 21**, **Spring Boot** y **Thymeleaf** (motor de plantillas que genera el HTML en el servidor), y organiza el código en capas: *controller* (controlador), *service* (servicio) y *repository* (repositorio).

---

## 1. Estado del laboratorio

| Elemento | Estado | Detalle |
|---|---|---|
| Proyecto Spring Boot con Maven | Hecho | `pom.xml` con Spring Boot 4.1.2 y Java 21 |
| Página de inicio (`/`) | Hecho | `HomeController` + `home.html`, con mensaje enviado desde el controlador |
| Página "Acerca de" (`/about`) | Hecho | `HomeController` + `about.html` |
| Catálogo de mascotas (`/pets`) | Hecho | `PetController` + `pets/list.html`, con mensaje cuando el catálogo está vacío |
| Detalle de una mascota (`/pets/{id}`) | Hecho | `PetController` + `pets/detail.html`; si el id no existe responde **404** |
| Modelo `Pet` | Hecho | Clase inmutable (sus campos no cambian después de crearla) con `id`, `name`, `species`, `age`, `description` y `size` |
| Servicio `PetService` | Hecho | Consulta los datos a través del repositorio |
| Repositorio en memoria | Hecho | `InMemoryPetRepository` con 5 mascotas de ejemplo (no usa base de datos) |
| Plantillas reutilizables (*fragments*) | Hecho | `fragments/head.html` y `fragments/navigation.html` |
| Estilos | Hecho | `static/css/application.css` (diseño adaptable a celular) |
| Pruebas | Hecho | `PetServiceTests` (3 pruebas) y `PetmatchLabApplicationTests` (carga del contexto) |

**Limitaciones actuales:** los datos viven solo en memoria (se pierden al apagar la aplicación) y el catálogo es de solo lectura: todavía no se pueden crear, editar ni eliminar mascotas.

---

## 2. Estructura del proyecto

```text
Laboratorio-7-main/
├── .mvn/wrapper/                      # Configuración del Maven Wrapper
├── mvnw                               # Ejecutar Maven en Linux / macOS
├── mvnw.cmd                           # Ejecutar Maven en Windows
├── pom.xml                            # Dependencias y configuración de Maven
└── src/
    ├── main/
    │   ├── java/com/example/petmatch_lab/
    │   │   ├── PetmatchLabApplication.java      # Clase principal (punto de arranque)
    │   │   ├── controller/
    │   │   │   ├── HomeController.java          # Rutas "/" y "/about"
    │   │   │   └── PetController.java           # Rutas "/pets" y "/pets/{id}"
    │   │   ├── model/
    │   │   │   └── Pet.java                     # Modelo de la mascota
    │   │   ├── repository/
    │   │   │   └── InMemoryPetRepository.java   # Datos de ejemplo en memoria
    │   │   └── service/
    │   │       └── PetService.java              # Lógica entre controlador y repositorio
    │   └── resources/
    │       ├── application.yaml                 # Nombre de la app y puerto 8080
    │       ├── static/css/application.css       # Estilos
    │       └── templates/
    │           ├── home.html
    │           ├── about.html
    │           ├── fragments/
    │           │   ├── head.html                # <head> compartido
    │           │   └── navigation.html          # Menú de navegación compartido
    │           └── pets/
    │               ├── list.html                # Catálogo
    │               └── detail.html              # Detalle de una mascota
    └── test/java/com/example/petmatch_lab/
        ├── PetmatchLabApplicationTests.java     # Verifica que la app arranca
        └── service/PetServiceTests.java         # Pruebas del servicio
```

### Cómo se conectan las capas

```text
Navegador → Controller → Service → Repository → datos en memoria
                │
                └→ Template de Thymeleaf (HTML) → Navegador
```

1. El navegador pide una ruta, por ejemplo `/pets`.
2. El **controller** (controlador) recibe la petición y le pide los datos al **service** (servicio).
3. El **service** consulta al **repository** (repositorio), que guarda las mascotas.
4. El controlador entrega los datos a una **template** (plantilla) de Thymeleaf, que genera el HTML final.

### Rutas disponibles

| Ruta | Descripción |
|---|---|
| `/` | Página de inicio |
| `/about` | Información sobre la aplicación |
| `/pets` | Lista de mascotas |
| `/pets/{id}` | Detalle de una mascota (ids de ejemplo: 1 a 5). Un id inexistente devuelve error 404 |

---

## 3. Cómo ejecutar la aplicación web

### Requisitos previos

- **JDK 21** (Java Development Kit, el kit de desarrollo de Java). Para comprobarlo:
  ```bash
  java -version
  ```
  Debe mostrar la versión 21 o superior.
- Conexión a internet la primera vez, porque Maven descarga las dependencias.
- No necesitas instalar Maven: el proyecto incluye el **Maven Wrapper** (`mvnw`), que lo descarga solo.

### Paso 1. Abrir una terminal en la carpeta del proyecto

La carpeta donde está el archivo `pom.xml`:

```bash
cd Laboratorio-7-main
```

### Paso 2. Iniciar la aplicación

**Linux / macOS:**
```bash
./mvnw spring-boot:run
```

**Windows (CMD o PowerShell):**
```bash
mvnw.cmd spring-boot:run
```

> En Linux/macOS, si aparece "Permission denied" (permiso denegado), ejecuta antes `chmod +x mvnw`.

Cuando en la consola aparezca un mensaje parecido a `Started PetmatchLabApplication`, la aplicación ya está funcionando.

### Paso 3. Abrir la aplicación en el navegador

```text
http://localhost:8080
```

Para detenerla, vuelve a la terminal y presiona `Ctrl + C`.

### Ejecutar las pruebas

```bash
./mvnw test          # Linux / macOS
mvnw.cmd test        # Windows
```

### Generar el archivo `.jar` (opcional)

```bash
./mvnw clean package
java -jar target/petmatch-lab-0.0.1-SNAPSHOT.jar
```

### Cambiar el puerto

Si el puerto 8080 está ocupado, edita `src/main/resources/application.yaml` y cambia el valor de `server.port`:

```yaml
server:
  port: 8081
```

---

## 4. Tecnologías usadas

- Java 21
- Spring Boot 4.1.2 (`spring-boot-starter-webmvc`)
- Thymeleaf (`spring-boot-starter-thymeleaf`)
- Maven (con Maven Wrapper)
- JUnit 5 para las pruebas
