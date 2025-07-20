# Sistema CRUD de Amigos

Una aplicación de escritorio desarrollada en Java que permite gestionar una lista de amigos mediante operaciones CRUD (Crear, Leer, Actualizar, Eliminar) con interfaz gráfica Swing y persistencia en archivos de texto.

## Arquitectura del Sistema

El proyecto implementa el **patrón arquitectónico MVC (Modelo-Vista-Controlador)** para garantizar la separación de responsabilidades y facilitar el mantenimiento del código.

### Componentes Principales:

- **Modelo (Model)**: `Amigo` - Entidad que representa los datos del dominio
- **Vista (View)**: `Ventana` y `Panel` - Interfaz gráfica de usuario con Swing
- **Controlador (Controller)**: `AmigoController` - Maneja la lógica de negocio y eventos

## Patrón DAO (Data Access Object)

El sistema utiliza el **patrón DAO** para abstraer y encapsular el acceso a los datos:

### Estructura DAO:
- **`AmigoDAO`** (Interfaz): Define el contrato para las operaciones de persistencia
- **`AmigoService`** (Implementación): Implementa las operaciones CRUD con persistencia en archivos

### Ventajas del Patrón DAO:
- **Separación de responsabilidades**: La lógica de acceso a datos está separada de la lógica de negocio
- **Flexibilidad**: Permite cambiar fácilmente el mecanismo de persistencia sin afectar otras capas
- **Testabilidad**: Facilita las pruebas unitarias mediante inyección de dependencias

## 📁 Estructura del Proyecto

```
src/
├── controller/
│   └── AmigoController.java
├── dao/
│   └── AmigoDAO.java
├── data/                <-- Aquí va la base de datos (archivo .txt)
├── model/
│   └── Amigo.java
├── service/
│   └── AmigoService.java
├── view/
│   ├── Panel.java
│   └── Ventana.java
└── Main.java
```

## Diagrama UML 

<img width="544" height="753" alt="UML" src="https://github.com/user-attachments/assets/0c6772d7-1b81-45a8-a5ab-7397419fdbbb" />

## Funcionalidades

- **Crear Amigo**: Registra un nuevo amigo con ID único y nombre obligatorio
- **Listar Amigos**: Muestra todos los amigos registrados
- **Actualizar Amigo**: Modifica datos de un amigo existente (ID y/o nombre)
- **Eliminar Amigo**: Elimina un amigo por su ID
- **Validaciones**: Verificación de ID únicos, nombres obligatorios y formatos válidos

## Casos de uso

<img width="322" height="366" alt="Casos de uso" src="https://github.com/user-attachments/assets/d35fdcbf-441d-4a47-b9b1-f4d1675b37f3" />

## Persistencia de Datos

Los datos se almacenan en un archivo de texto plano (`src/data/bd.txt`) con formato:
```
nombre!id
```

### Características de la Persistencia:
- Uso de `RandomAccessFile` para operaciones de lectura/escritura
- Validación de integridad de datos
- Manejo robusto de errores de E/S
- Archivo temporal para operaciones de eliminación segura

## Reglas de Negocio

- **ID único**: No se permiten amigos con el mismo ID
- **Nombre obligatorio**: El campo nombre no puede estar vacío
- **Nombres duplicados**: Se permite tener amigos con el mismo nombre
- **Actualización flexible**: Permite actualizar ID y/o nombre independientemente
- **Eliminación segura**: Requiere confirmación del ID existente

## 🚀 Ejecución

1. Compilar el proyecto Java
2. Ejecutar la clase `Main`
3. Hacer clic en "INICIAR" para crear la base de datos
4. Utilizar los botones de la interfaz para realizar operaciones CRUD

## Tecnologías Utilizadas

- **Java SE**: Lenguaje de programación principal
- **Swing**: Framework para la interfaz gráfica de usuario
- **File I/O**: Persistencia mediante archivos de texto
- **Patrón MVC**: Arquitectura de separación de capas
- **Patrón DAO**: Abstracción de acceso a datos