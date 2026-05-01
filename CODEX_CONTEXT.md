# Contexto para continuar con Codex

Fecha de contexto: 2026-05-01

## Proyecto

- Repo local: `/Users/jesusgarciabarba/Desktop/bola_de_drac_api`
- Remoto: `https://github.com/jesusgarba/bola_de_drac_api.git`
- Rama de trabajo: `prueba_codex`
- Rama base usada: `final_version`
- Paquete Android: `com.example.myapplication`
- Dispositivo fisico usado: `RFCT40VZN4E` / `SM-M236B - Android 14`

## Estado Git

- La rama `prueba_codex` ya existe en remoto.
- Ultimo commit subido antes de esta sesion:
  - `2bca7b7 Add character detail screen`
- En esta sesion se preparo un commit para:
  - usar el logo de Dragon Ball API como icono launcher de la app,
  - guardar este contexto para futuras sesiones.
- El commit de esta sesion se debe confirmar con:
  - `git log -1 --oneline --decorate`
- Antes de seguir, revisar:
  - `git status --short --branch`
  - Esperado tras el push: `## prueba_codex...origin/prueba_codex`.
  - Puede quedar localmente sin trackear `.idea/AndroidProjectSystem.xml`; no forma parte de los cambios de la app.

## Trabajo realizado

Se implemento la pantalla de detalle de personaje para que, al pulsar un personaje del listado, se navegue al detalle y se carguen datos reales desde la API.

Flujo actual:

1. El listado usa `InitScreen`.
2. Cada personaje llama a `navigateToDetail(characterModel.id!!)`.
3. `NavigationWrapper` navega a `Detail(id)`.
4. `DetailScreen` recibe el `id` y el `BolaDracApiViewModel`.
5. La pantalla llama a `bolaDracApiViewModel.getCharacterDetail(id)`.
6. El ViewModel usa el repositorio.
7. El repositorio llama a Retrofit.
8. Retrofit consume `GET api/characters/{id}`.
9. La UI pinta estados `Loading`, `Error` y `Success`.

Tambien se actualizo el icono launcher de la aplicacion usando el asset ya existente:

- `app/src/main/res/drawable/icon_bola_drac.webp`

El adaptive icon ahora usa fondo blanco y foreground bitmap generado por densidad. Tambien se reemplazaron los `ic_launcher.webp` y `ic_launcher_round.webp` para los launchers/dispositivos que usen esos recursos.

## Archivos tocados

- `app/src/main/java/com/example/myapplication/core/navigation/NavigationWrapper.kt`
  - Ahora pasa el ViewModel a `DetailScreen`.

- `app/src/main/java/com/example/myapplication/data/network/DragonBallApiService.kt`
  - Se anadio `getCharacterById(@Path("id") id: Int)`.

- `app/src/main/java/com/example/myapplication/data/network/DragonBallApiRepository.kt`
  - Se anadio `suspend fun getCharacterById(id: Int): Character`.

- `app/src/main/java/com/example/myapplication/data/network/response/CharacterDetailResponse.kt`
  - Nuevo mapper de respuesta de detalle.
  - Incluye planeta de origen y transformaciones.

- `app/src/main/java/com/example/myapplication/presentation/BolaDracApiViewModel.kt`
  - Se anadio `CharacterDetailUiState`.
  - Se anadio `characterDetailState`.
  - Se anadio `getCharacterDetail(id)`.

- `app/src/main/java/com/example/myapplication/presentation/DetailScreen.kt`
  - Sustituido el placeholder por una pantalla real.
  - Muestra imagen, nombre, raza/genero, ki, max ki, afiliacion, descripcion, planeta de origen y transformaciones.

- `app/src/main/java/com/example/myapplication/presentation/model/Character.kt`
  - Se ampliaron los modelos de presentacion con `Planet` y `Transformation`.

- `app/src/main/res/mipmap-anydpi/ic_launcher.xml`
  - Ahora usa `@color/white` como fondo y `@mipmap/ic_launcher_foreground` como foreground.

- `app/src/main/res/mipmap-anydpi/ic_launcher_round.xml`
  - Mismo cambio que el icono normal.

- `app/src/main/res/mipmap-*/ic_launcher.webp`
  - Regenerados con el logo de Dragon Ball API.

- `app/src/main/res/mipmap-*/ic_launcher_round.webp`
  - Regenerados con el logo de Dragon Ball API.

- `app/src/main/res/mipmap-*/ic_launcher_foreground.webp`
  - Nuevos foregrounds por densidad para el adaptive icon.

## Verificacion realizada

Comando ejecutado:

```bash
./gradlew app:assembleDebug
```

Resultado:

```text
BUILD SUCCESSFUL
```

Tambien se instalo y ejecuto en dispositivo fisico:

```bash
./gradlew app:installDebug
adb shell monkey -p com.example.myapplication -c android.intent.category.LAUNCHER 1
adb shell am start -n com.example.myapplication/.MainActivity
adb shell pidof com.example.myapplication
```

Resultado:

- APK instalado en `SM-M236B - Android 14`.
- App lanzada correctamente.
- Proceso activo confirmado en esta sesion con PID `16499`.

Nota: al inicio `adb devices` mostro el dispositivo `RFCT40VZN4E` como `unauthorized`. Se autorizo la depuracion USB desde el movil y despues `adb wait-for-device` permitio continuar.

## Detalle importante del entorno

El repo clonado no traia `local.properties`.
Se creo localmente:

```properties
sdk.dir=/Users/jesusgarciabarba/Library/Android/sdk
```

`local.properties` esta ignorado por `.gitignore`, no se subio al repo.

## Siguientes pasos posibles

- Probar manualmente tocar varios personajes y validar que cada detalle carga el personaje correcto.
- Revisar visualmente la pantalla de detalle en movil fisico.
- Revisar visualmente en el launcher que el icono nuevo se ve bien despues de reinstalar.
- Mejorar UI del detalle si se desea:
  - adaptar textos largos,
  - mejorar imagen del planeta,
  - separar datos en chips,
  - mostrar transformaciones horizontalmente.
- Si el detalle se recarga demasiado al volver/entrar, considerar cachear por `id` en el ViewModel.
- Si se quiere abrir PR:
  - `https://github.com/jesusgarba/bola_de_drac_api/pull/new/prueba_codex`

## Prompt util para otra sesion

Estoy trabajando en el repo Android `/Users/jesusgarciabarba/Desktop/bola_de_drac_api`, rama `prueba_codex`. Es una app Jetpack Compose con Hilt, Retrofit y Paging. La rama viene de `final_version`. Ya se implemento y subio el detalle de personaje en el commit `2bca7b7 Add character detail screen`: al pulsar un personaje del listado se navega a `Detail(id)`, se llama a `GET api/characters/{id}` y se pinta una pantalla con loading/error/success y los datos reales del personaje. En la siguiente sesion tambien se cambio el icono launcher para usar `app/src/main/res/drawable/icon_bola_drac.webp`, regenerando recursos `mipmap-*` y validando con `./gradlew app:assembleDebug`, `./gradlew app:installDebug` y `adb shell am start -n com.example.myapplication/.MainActivity`. Antes de continuar, revisa `CODEX_CONTEXT.md`, ejecuta `git status --short --branch` y valida con `./gradlew app:assembleDebug`.
