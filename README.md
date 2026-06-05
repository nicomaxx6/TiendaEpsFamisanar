# Sistema de Gestión de Tienda - Backend API

Este proyecto consiste en una API REST desarrollada bajo el ecosistema de **Spring Boot** para gestionar el inventario, procesar ventas con cálculo dinámico de impuestos (IVA), reabastecer stock bajo alertas críticas y exponer un tablero de estadísticas comerciales en tiempo real.

---

## Stack Tecnológico y Dependencias

El sistema utiliza las tecnologías más modernas del desarrollo empresarial en Java:

* **Java 17** como lenguaje base.
* **Spring Boot 4.0.6** (Ecosistema Spring 6.2+ / Jakarta EE 11).
* **Spring Data JPA & Hibernate** para el mapeo objeto-relacional y gestión transaccional.
* **Spring Boot Validation** para la restricción y seguridad de datos de entrada.
* **MySQL Connector J** como controlador nativo de conexión a la base de datos.
* **Project Lombok** para la reducción de código  (Getters, Setters, Builders y Constructores).
* **Springdoc OpenAPI UI 3.1.5** para la documentación interactiva y pruebas de la API (compatible con Jakarta EE 11).

---

## Instrucciones y Configuración de la Base de Datos

El sistema requiere un motor de base de datos relacional **MySQL (versión 8.0 o superior)**.

### 1. Creación del Esquema
Para montar la tabla de productos y cargar el lote completo de datos de prueba (Papelería, Droguería y Supermercado), abre y ejecuta el script proporcionado en la raíz del proyecto:

* **Archivos:** `Script insert tabla producto y Script tabla tipo_producto`
* **Instrucciones:** Abre este archivo en tu gestor de base de datos (ej. MySQL Workbench) y ejecútalo por completo. Esto creará la estructura requerida y poblará el inventario automáticamente para que los endpoints de consulta y estadísticas muestren datos consistentes de inmediato.
