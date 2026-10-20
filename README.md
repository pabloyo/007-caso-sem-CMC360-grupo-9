# Rackify

> **Escanea y registra.**

Rackify es una aplicación móvil desarrollada en Android (Jetpack Compose) diseñada para simplificar la gestión, escaneo y registro de activos e infraestructura en racks de manera rápida y eficiente.

*Nota: El proyecto se encuentra actualmente en fase temprana de desarrollo (MVP).*


## Equipo de Desarrollo

| Nombre | Usuario | Rol |
| :--- | :--- | :--- |
| **Pablo Pezoa Mundaca** | `@pabloyo` | Co-Developer / Lead |
| **Ignacio Bravo Morales** | `@redarmlet` | Co-Developer / Lead |

## Registro en 3 Pasos Rápidos

Rackify transforma la auditoría manual e inventariado de salas de servidores en un proceso ultra expedito de tres simples pasos:

- **1. ESCANEAS:** Capturas el código de barras del rack o servidor directamente con la cámara de tu celular.
- **2. VERIFICAS:** Confirmas o ajustas la información detectada de forma inmediata en la pantalla.
- **3. GUARDAS:** Registras el activo y la información se sincroniza automáticamente con el sistema.

## ¿Cómo funciona Rackify? (Funcionalidades)

La plataforma está diseñada para optimizar el flujo de trabajo de técnicos e ingenieros en *Data Centers*, eliminando la planilla en papel y reduciendo los tiempos de registro al mínimo:

* **Captura mediante Cámara Móvil:** Reconocimiento rápido de códigos de barras y QR en la estructura del rack o en las unidades de servidores.
* **Confirmación In Situ:** Una vista detallada permite al operador validar, editar datos específicos (ID, ubicación, estado) y confirmar el registro antes de enviarlo.
* **Dashboard Central:** Todos los datos capturados quedan centralizados en un panel de control interactivo donde se puede revisar el estado, métricas e historial de todos los racks registrados.
* **Reimpresión de Etiquetas:** Si un código de barras físico se deteriora por uso o temperatura, la plataforma permite re-generar e imprimir la etiqueta original directamente desde el sistema para mantener la trazabilidad.


## 🎨 Identidad Visual & Branding

<img width="1024" height="2098" alt="rackify_branding" src="https://github.com/user-attachments/assets/ac991e1d-b35f-473d-acb9-517192dfbf6b" />


## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Kotlin
- **UI:** Jetpack Compose & Material 3
- **Arquitectura / Gradle:** Kotlin DSL (`build.gradle.kts`)
- **Compatibilidad:** Android 8.0+ (API level 26+)
