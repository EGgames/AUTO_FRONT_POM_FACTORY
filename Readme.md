# AUTO_FRONT_POM_FACTORY

Suite de automatización E2E del frontend con **Page Object Model + Page Factory + Cucumber BDD + SerenityBDD**.  
Genera un reporte visual completo accesible desde el navegador en `http://localhost:9000`.

---

## Requisitos previos

| Herramienta | Versión mínima | Verificar |
|---|---|---|
| Java (JDK) | 17 | `java -version` |
| Firefox | cualquier reciente | `firefox --version` |
| Python 3 | 3.x | `python3 --version` |
| Node.js + npm | 18+ | `node -v` |

> No es necesario instalar GeckoDriver manualmente — WebDriverManager lo descarga automáticamente.

## Link del proyecto : https://github.com/AITraining-SofkaProyects-Team2/Diagnostico-Semana0 
---

## Estructura del proyecto

```
AUTO_FRONT_POM_FACTORY/
├── Frontend/                          # Aplicación React/Vite (SUT)
├── src/test/
│   ├── java/com/miejemplo/
│   │   ├── hooks/          Hooks de Cucumber (setup/teardown del driver)
│   │   ├── ui/             Page Objects con Page Factory (@FindBy + PageFactory)
│   │   ├── stepdefinitions/Implementación de los pasos Gherkin (usa Pages directamente)
│   │   ├── runners/        TestRunner (punto de entrada de Cucumber)
│   │   └── util/           DriverManager, ConfigReader
│   └── resources/
│       ├── config.properties          Configuración (URL base, headless, etc.)
│       ├── serenity.properties        Configuración de SerenityBDD
│       └── features/
│           ├── consulta.feature       Consulta y filtrado de pedidos (Dashboard)
│           ├── navegacion.feature     Navegación entre páginas
│           ├── agregar_usuario.feature Formulario de registro de administrador
│           └── agregar_pedido.feature  Formulario de creación de pedidos
├── build.gradle                       Dependencias y tareas Gradle
└── target/site/serenity/              Reporte Serenity (se genera al correr tests)
```

---

## Paso 1 — Levantar el Frontend

Los tests de E2E necesitan que la aplicación esté corriendo en `http://localhost:5173`.

```bash
cd Frontend
npm install        # solo la primera vez
npm run dev
```

Dejar esta terminal abierta mientras corren los tests.

> Si el puerto 5173 está ocupado, Vite usa el siguiente disponible (5174, 5175...).
> En ese caso pasá la URL real al ejecutar los tests:
> ```bash
> ./gradlew test -Dbase.url=http://localhost:5174
> ```

---

## Paso 2 — Ejecutar los tests

Desde la raíz del proyecto (donde está `build.gradle`):

```bash
./gradlew test
```

Esto ejecuta los **23 escenarios** distribuidos en los 4 feature files y al finalizar **genera el reporte Serenity automáticamente**.

### Opciones de ejecución

| Propósito | Comando |
|---|---|
| Ejecución normal | `./gradlew test` |
| URL del frontend personalizada | `./gradlew test -Dbase.url=http://localhost:5174` |
| Modo headless (sin ventana del navegador) | `./gradlew test -Dheadless=true` |
| Headless + URL personalizada | `./gradlew test -Dheadless=true -Dbase.url=http://localhost:5174` |
| Solo regenerar el reporte, sin correr tests | `./gradlew aggregate --rerun-tasks` |

### Resultado esperado en la consola

```
> Task :test

TestRunner > Navegación entre páginas > ... PASSED
TestRunner > Formulario de registro de administrador > ... PASSED
...

23 tests completed, 1 failed

> Task :aggregate
Generating Serenity Reports
  - Test Root: target/site/serenity
  - Main report: file:///...target/site/serenity/index.html
```

> El 1 test fallido en `consulta.feature` ("debería ver al menos un pedido en pantalla")
> requiere que el **backend de la API** esté activo. El resto de los tests no dependen del backend.

---

## Paso 3 — Ver el reporte en el navegador

### Opción A — Tarea Gradle (recomendada)

```bash
./gradlew serveReports
```

Abre el navegador en **http://localhost:9000** y presioná `Ctrl+C` en la terminal para detener el servidor cuando termines.

### Opción B — Servidor manual con Python

```bash
cd target/site/serenity
python3 -m http.server 9000
```

Luego abrí **http://localhost:9000** en el navegador.

### Opción C — Abrir el archivo directamente

```bash
open target/site/serenity/index.html
```

> Si el reporte muestra **0 tests** después de correr, forzá una recarga sin caché en el navegador:
> - Mac: `Cmd + Shift + R`
> - Windows/Linux: `Ctrl + Shift + R`

---

## Cobertura de los tests

| Feature | Escenarios | Descripción |
|---|---|---|
| `consulta.feature` | 3 | Visualización y filtrado de pedidos en el Dashboard |
| `navegacion.feature` | 5 | Navegación entre Dashboard, /addUser y /addOrder |
| `agregar_usuario.feature` | 7 | Campos, validaciones y toggle de contraseña en /addUser |
| `agregar_pedido.feature` | 8 | Campos, validaciones y textos en /addOrder |
| **Total** | **23** | |

---

## Configuración

### `src/test/resources/config.properties`

```properties
base.url=http://localhost:5173   # URL del frontend
headless=false                     # true para correr sin abrir ventana
explicit.wait=10                   # segundos de espera máxima
browser=firefox
```

### `src/test/resources/serenity.properties`

```properties
serenity.project.name=Auto Front POM Factory
webdriver.driver=firefox
webdriver.base.url=http://localhost:5173
serenity.take.screenshots=FOR_FAILURES   # captura pantalla solo en fallos
serenity.outputDirectory=target/site/serenity
```

---

## Flujo completo de un solo comando

Si el frontend **ya está corriendo**, podés ejecutar todo así:

```bash
# 1. Correr tests + generar reporte
./gradlew test

# 2. Ver el reporte (en otra terminal o en la misma con & al final)
./gradlew serveReports
```

O en una sola línea:

```bash
./gradlew test ; ./gradlew serveReports
```
