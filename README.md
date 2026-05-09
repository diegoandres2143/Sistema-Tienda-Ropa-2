# Sistema de Gestión de Tienda de Ropa

## Descripción del Proyecto

El presente proyecto consiste en una aplicación de escritorio desarrollada en Java para la gestión integral de una tienda de ropa. El sistema permite administrar clientes, productos y ventas, siguiendo los principios SOLID de diseño de software y empleando una arquitectura multicapa. La persistencia de datos se realiza a través de una base de datos relacional alojada en Neon (PostgreSQL).

## Estructura del Proyecto

El proyecto sigue una arquitectura limpia y desacoplada, dividida en los siguientes paquetes:

- `modelo`: Contiene las clases de dominio del sistema: Cliente, Producto, Venta y DetalleVenta.
- `repositorio`: Implementa el patrón Repository para la abstracción de acceso a datos. Incluye interfaces e implementaciones concretas para cada entidad.
- `servicio`: Contiene la lógica de negocio y las validaciones del sistema. Actúa como intermediario entre los controladores y los repositorios.
- `controlador`: Gestiona los eventos de la interfaz gráfica y coordina las acciones entre la vista y los servicios.
- `vista`: Implementa la interfaz de usuario utilizando Swing. Se compone de una ventana principal con pestañas para la gestión de clientes, productos y ventas.
- `conexion`: Gestiona la conexión con la base de datos Neon mediante JDBC.

## Tecnologías Utilizadas

- Java Development Kit (JDK) 17
- Swing para la interfaz gráfica
- Maven para la gestión de dependencias y construcción del proyecto
- PostgreSQL (Neon) como sistema gestor de base de datos
- JDBC para la conexión y operaciones con la base de datos

## Requisitos del Sistema

Para ejecutar correctamente el proyecto, se deben cumplir los siguientes requisitos:

- Tener instalado JDK 17 o superior.
- Tener acceso a Internet para conectarse a la base de datos Neon.
- Tener configurado correctamente el archivo de conexión con las credenciales de la base de datos.
- Opcional: NetBeans u otro IDE con soporte para Maven.

## Configuración de la Base de Datos

La aplicación utiliza una base de datos en Neon. Antes de ejecutar el sistema, es necesario crear las siguientes tablas en la base de datos:

```
CREATE TABLE IF NOT EXISTS clientes (
    cedula VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS productos (
    codigo VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    talla VARCHAR(10),
    color VARCHAR(20),
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE IF NOT EXISTS ventas (
    id SERIAL PRIMARY KEY,
    cliente_cedula VARCHAR(20) REFERENCES clientes(cedula),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS detalle_ventas (
    id SERIAL PRIMARY KEY,
    venta_id INT REFERENCES ventas(id) ON DELETE CASCADE,
    producto_codigo VARCHAR(20) REFERENCES productos(codigo),
    cantidad INT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL
);
```

Además, se debe configurar la conexión en la clase `Conexion.java` con los parámetros adecuados de URL, usuario y contraseña.

## Instalación y Ejecución

### Clonar el repositorio

```bash
git clone <url-del-repositorio>
cd tiendaropa2
```

### Compilar y ejecutar con Maven

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.diego.tiendaropa2.Main"
```

### Ejecutar desde NetBeans

1. Abrir el proyecto en NetBeans.
2. Limpiar y construir el proyecto (Clean and Build).
3. Ejecutar la clase `Main.java` ubicada en el paquete `com.diego.tienda ropa2`.

## Funcionalidades del Sistema

### Carrito de Compras e Histórico de Ventas

El sistema ahora incorpora un **carrito de compras** que permite agregar múltiples productos a una venta antes de registrarla, facilitando la gestión de ventas compuestas y el control de stock en tiempo real. Además, se ha añadido una sección de **histórico de ventas**, donde se pueden consultar todas las ventas realizadas, visualizar sus detalles y el total acumulado, mejorando el seguimiento y la trazabilidad de las operaciones comerciales.

### Gestión de Clientes

- Registrar nuevos clientes con cédula, nombre y correo electrónico.
- Validar que la cédula no esté duplicada y que el correo tenga un formato adecuado.
- Editar la información de un cliente existente.
- Eliminar clientes siempre que no tengan ventas asociadas.
- Listar todos los clientes registrados en una tabla interactiva.

### Gestión de Productos

- Registrar productos con código, nombre, talla, color, precio y stock.
- Validar que el precio sea positivo y que el stock no sea negativo.
- Editar la información de un producto.
- Eliminar productos que no estén asociados a ninguna venta.
- Listar todos los productos en una tabla con opción de selección.

### Gestión de Ventas

- Seleccionar un cliente desde un menú desplegable.
- Agregar productos al carrito de compra especificando la cantidad.
- Validar disponibilidad de stock antes de agregar un producto.
- Visualizar el detalle de la venta en una tabla con código, nombre, cantidad, precio y subtotal.
- Calcular automáticamente el total de la venta.
- Registrar la venta, lo que actualiza el stock de los productos y guarda el encabezado y los detalles en la base de datos.
- Limpiar el carrito de compra en cualquier momento.

## Principios SOLID Aplicados

- Principio de Responsabilidad Única: Cada clase tiene una razón clara para existir (modelo, repositorio, servicio, controlador, vista).
- Principio de Abierto/Cerrado: Las interfaces de repositorio permiten extender funcionalidades sin modificar el código existente.
- Principio de Sustitución de Liskov: Las implementaciones concretas pueden reemplazar a las interfaces sin afectar el sistema.
- Principio de Segregación de Interfaces: Las interfaces de repositorio son específicas para cada entidad.
- Principio de Inversión de Dependencias: Los servicios dependen de interfaces de repositorio, no de implementaciones concretas.

## Pruebas

El proyecto incluye una clase de prueba unitaria denominada `PruebaSistemaTienda.java` que ejecuta operaciones de creación, edición, eliminación y consulta sobre clientes, productos y ventas. Al finalizar, la prueba limpia automáticamente todos los registros generados para no dejar datos residuales en la base de datos.

Para ejecutar la prueba:

```bash
mvn exec:java -Dexec.mainClass="com.diego.tiendaropa2.pruebas.PruebaSistemaTienda"
```

## Estructura de Archivos del Proyecto

```
tiendaropa2/
├── pom.xml
├── README.md
├── .gitignore
└── src/
    └── main/
        └── java/
            └── com/
                └── diego/
                    └── tiendaropa2/
                        ├── Main.java
                        ├── conexion/
                        ├── controlador/
                        ├── modelo/
                        ├── repositorio/
                        ├── servicio/
                        ├── vista/
                        └── pruebas/

```
