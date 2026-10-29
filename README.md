# Rackify

Aplicación móvil nativa para Android desarrollada con Kotlin y Jetpack Compose, orientada al escaneo, inventario y gestión de racks de servidores e infraestructura TI.

## Equipo de Desarrollo

| Nombre | Usuario | Rol |
| :--- | :--- | :--- |
| **Pablo Pezoa Mundaca** | `@pabloyo` | Co-Developer / Lead |
| **Ignacio Bravo Morales** | `@redarmlet` | Co-Developer / Lead |

## Arquitectura del Proyecto

El proyecto implementa la arquitectura **MVVM (Model-View-ViewModel)** junto con **Single Activity Architecture**:

* **Capa de Presentación (UI)**: Construida íntegramente de forma declarativa con Jetpack Compose. Cada pantalla reacciona a un estado inmutable (`UiState`) expuesto por su respectivo ViewModel.
* **Capa de Lógica (ViewModels)**: Encargada del manejo de estados, procesamiento de acciones de usuario y comunicación asíncrona mediante Kotlin Coroutines y Flow.
* **Capa de Datos (Data / Local)**: Implementa el patrón Repository (`UserRepository`) como fuente única de verdad para la persistencia local.
* **Navegación**: Gestionada desde `MainActivity` mediante `NavHost` de Jetpack Compose Navigation, con animaciones de transición direccionales según la posición de las pestañas.

## Persistencia de Datos y Gestión de Sesión

La persistencia local se gestiona utilizando la librería **AndroidX Room** con procesado de anotaciones mediante **KSP (Kotlin Symbol Processing)**:

* **`UserEntity`**: Tabla `users` para el almacenamiento local de cuentas registradas (ID, usuario, contraseña, nombre y rol).
* **`SessionEntity`**: Tabla `session` de fila única estática (`id = 1`) que mantiene el nombre del usuario activo.
* **`UserDao`**: Interfaz de acceso a datos con funciones suspendidas (`suspend`) para consultas asíncronas de usuarios, inserción de registros (`OnConflictStrategy.REPLACE`) y control de sesión.
* **`AppDatabase`**: Clase abstracta contenedora de la base de datos SQLite (`rackify_database`), con configuración de recreación limpia en desarrollo (`fallbackToDestructiveMigration`).
* **`UserRepository`**: Unifica las operaciones de usuario y el ciclo de vida de la sesión activa.

### Ciclo de Sesión
1. **Inicio de aplicación**: `MainViewModel` consulta `SessionEntity` al arrancar. Si existe una sesión activa, redirige automáticamente al área principal de la app (Escáner/Dashboard).
2. **Autenticación**: `LoginViewModel` verifica el usuario y la contraseña contra `UserEntity` en Room y guarda la sesión en `SessionEntity`.
3. **Cierre de sesión**: La llamada a `logout()` en `MainViewModel` purga la tabla `session` y redirige al usuario al Login.

## Módulos y Pantallas

* **Login (`LoginScreen` / `LoginViewModel`)**: Pantalla de acceso. Autentica credenciales contra la base de datos local y maneja estados de error.
* **Registro (`RegistroScreen` / `RegistroViewModel`)**: Formulario de creación de cuenta con validaciones in situ y persistencia inmediata en Room.
* **Escáner (`ScannerScreen`)**: Interfaz principal para el escaneo de códigos de infraestructura.
* **Dashboard (`DashboardScreen` / `DashboardViewModel`)**: Listado de racks registrados con estado de inspección y último cambio.
* **Resumen (`ResumenScreen` / `ResumenViewModel`)**: Vista detallada de especificaciones y datos capturados del rack seleccionado.
* **Perfil (`ProfileScreen` / `ProfileViewModel`)**: Despliegue dinámico de la información del usuario autenticado (nombre, usuario, rol y organización) obtenida desde Room.
* **Configuración (`ConfigurationScreen` / `ThemeViewModel`)**: Panel de ajustes que permite alternar el tema visual de la aplicación.

## Validaciones de Dominio

La lógica de validación de entradas está centralizada en el objeto de dominio `FormValidator`:

* **Nombre**: Mínimo 2 caracteres, obligatorio y restringido a caracteres alfabéticos.
* **Usuario**: Mínimo 5 caracteres y obligatorio.
* **Contraseña**: Mínimo 6 caracteres y validación de coincidencia en confirmación de registro.

## Diseño e Identidad Visual

* **Material Design 3**: Uso de componentes y esquemas de color de la especificación Material 3.
* **Soporte de Tema**: Alternancia entre modo claro y modo oscuro gestionado dinámicamente mediante `ThemeViewModel`.
* **Componentes Reutilizables**: Encapsulamiento de campos de texto (`RackifyTextField`), botones (`RackifyButton`), barras superiores (`RackifyTopBar`), barras inferiores (`RackifyBottomBar`) y tarjetas (`RackCard`).

<p align="center">
  <img width="360" alt="Identidad Visual y Branding de Rackify" src="https://github.com/user-attachments/assets/ac991e1d-b35f-473d-acb9-517192dfbf6b" />
</p>

## Stack Tecnológico

* **Lenguaje**: Kotlin
* **UI**: Jetpack Compose & Material 3
* **Navegación**: Navigation Compose
* **Persistencia**: AndroidX Room
* **Procesado de Anotaciones**: KSP
* **Asincronía**: Kotlin Coroutines & StateFlow / SharedFlow
* **Build System**: Gradle con Kotlin DSL (`build.gradle.kts`)
* **Compatibilidad Mínima**: Android 7.0 (API level 24+)
