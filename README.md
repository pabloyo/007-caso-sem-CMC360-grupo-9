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
* **Capa de Datos (Data / Local & Remote)**: Implementa el patrón Repository (`UserRepository` y `RackRepository`) como fuente de verdad, combinando la persistencia local en Room con llamadas asíncronas HTTP hacia la API remota.
* **Navegación**: Gestionada desde `MainActivity` mediante `NavHost` de Jetpack Compose Navigation, con eventos de navegación desacoplados (`NavigationEvent`) y animaciones de transición direccionales según la posición de las pestañas.

## Persistencia de Datos y Gestión de Sesión

La persistencia se gestiona mediante un esquema híbrido que combina la base de datos en la nube (**Xano**) para la autenticación remota y la librería **AndroidX Room** con procesado de anotaciones **KSP (Kotlin Symbol Processing)** para el almacenamiento local:

* **`UserEntity`**: Tabla `users` para el almacenamiento local de cuentas de usuario (ID, usuario, contraseña, nombre y rol), sirviendo como caché local de los usuarios registrados y validados en la base de datos remota de Xano.
* **`SessionEntity`**: Tabla `session` de fila única estática (`id = 1`) que mantiene los datos del usuario con sesión activa para evitar reautenticaciones continuas.
* **`UserDao`**: Interfaz de acceso a datos con funciones suspendidas (`suspend`) para consultas asíncronas de usuarios, inserción de registros (`OnConflictStrategy.REPLACE`) y control de estado de la sesión.
* **`AppDatabase`**: Clase abstracta contenedora de la base de datos SQLite (`rackify_database`), con configuración de recreación limpia en desarrollo (`fallbackToDestructiveMigration`).
* **`UserRepository`**: Coordina las operaciones de usuario actuando como intermediario entre las llamadas a la API remota de Xano (`POST auth/login`, `POST auth/signup`) y la persistencia local en Room.

### Ciclo de Sesión Híbrido (Nube + Room)
1. **Inicio de aplicación**: `MainViewModel` consulta `SessionEntity` en Room al arrancar. Si existe una sesión activa guardada de una autenticación previa en la nube, redirige automáticamente al área principal de la app (Escáner/Dashboard).
2. **Autenticación y Registro**: `LoginViewModel` y `RegistroViewModel` verifican las credenciales contra la base de datos remota en Xano. Tras la validación exitosa del servidor, se guarda la información del usuario en `UserEntity` y se registra el estado de sesión activa en `SessionEntity`.
3. **Cierre de sesión**: La llamada a `logout()` en `MainViewModel` purga la tabla `session` en Room, finaliza el estado de la sesión activa y redirige al usuario al Login.

## Integración con Backend (API REST en Xano)

La aplicación sincroniza los datos de infraestructura con un backend en **Xano** configurado a través de **Retrofit 2**:

* **Base URL**: `https://x8ki-letl-twmt.n7.xano.io/api:_o5YdykW/`
* **Endpoints de Autenticación**:
  * `POST auth/signup`: Registro de usuarios en el servidor remoto.
  * `POST auth/login`: Validación remota de credenciales.
* **Endpoints de Racks**:
  * `GET rack`: Obtiene la lista completa de racks registrados.
  * `GET rack/{rack_id}`: Consulta los datos de un rack específico por ID.
  * `POST rack`: Registra un nuevo rack en la plataforma.
  * `PATCH rack/{rack_id}`: Actualiza el estado (*PENDIENTE*, *VERIFICADO*, *RECHAZADO*) o campos del rack.
  * `DELETE rack/{rack_id}`: Elimina un registro de rack de la base de datos remota.

## Módulos y Pantallas

* **Login (`LoginScreen` / `LoginViewModel`)**: Pantalla de acceso. Autentica credenciales contra el backend remoto de Xano y la base de datos local en Room, manejando estados de error y carga.
* **Registro (`RegistroScreen` / `RegistroViewModel`)**: Formulario de creación de cuenta con validaciones in situ, registro en la nube (Xano) y persistencia local en Room.
* **Escáner (`ScannerScreen`)**: Interfaz principal para el escaneo e inspección de códigos de infraestructura.
* **Dashboard (`DashboardScreen` / `DashboardViewModel`)**: Listado de racks registrados con estado de inspección, filtrado y opciones de edición/eliminación.
* **Resumen (`ResumenScreen` / `ResumenViewModel`)**: Vista detallada de especificaciones y datos capturados del rack seleccionado (marca, capacidad U, dimensiones, capacidad de carga).
* **Perfil (`ProfileScreen` / `ProfileViewModel`)**: Despliegue dinámico de la información del usuario autenticado (nombre, usuario, rol y organización) obtenida desde Room.
* **Configuración (`ConfigurationScreen` / `ThemeViewModel`)**: Panel de ajustes que permite alternar el tema visual de la aplicación (Modo claro/oscuro).
* **Carga (`LoadingScreen`)**: Interfaz de transición interactiva utilizada durante procesos de escaneo e inspección.

## Validaciones de Dominio

La lógica de validación de entradas está centralizada en los objetos de dominio `FormValidator` y `RackifyValidators`:

* **Nombre**: Mínimo 2 caracteres, obligatorio y restringido a caracteres alfabéticos.
* **Usuario**: Mínimo 5 caracteres y obligatorio.
* **Contraseña**: Mínimo 6 caracteres y validación de coincidencia en confirmación de registro.

## Recursos Nativos y Notificaciones

* **Notificaciones del Sistema (`NotificationHelper`)**: Creación de un canal de notificaciones nativo (`rackify_channel`) que emite alertas locales al escanear y registrar un rack en la plataforma. Incluye verificación de permisos `POST_NOTIFICATIONS` para Android 13+ (API 33+).

## Pruebas Unitarias

El proyecto incluye pruebas automatizadas localizadas en `app/src/test/java/com/example/rackify/`:

* **`RackApiTest`**: Verifica las peticiones HTTP y la deserialización de respuestas JSON utilizando **MockWebServer**.
* **`DashboardViewModelTest`**: Valida la lógica de presentación y estados `StateFlow` del ViewModel utilizando un doble de prueba de la API y `kotlinx-coroutines-test`.

## Ejecución del Proyecto

* **Entorno**: Android Studio Ladybug (2024.2.1) o superior con JDK 17.
* **Dispositivo / Emulador**: Android 7.0 (API level 24+) con conexión a internet.
* **Pasos de Ejecución**:
  1. Clonar el repositorio.
  2. Abrir el proyecto en Android Studio y esperar la sincronización de Gradle.
  3. Seleccionar el emulador o dispositivo físico y presionar **Run**.

## Diseño e Identidad Visual

* **Material Design 3**: Uso de componentes y esquemas de color de la especificación Material 3.
* **Tipografía**: Integración de la fuente monospaciada `JetBrains Mono`.
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
* **Consumo de API**: Retrofit 2, Gson & OkHttp (Logging Interceptor)
* **Backend**: Xano (API REST)
* **Testing**: JUnit, MockWebServer & kotlinx-coroutines-test
* **Build System**: Gradle con Kotlin DSL (`build.gradle.kts`)
* **Compatibilidad Mínima**: Android 7.0 (API level 24+)
