# 🎮 X-0 – App Android en Kotlin

Aplicación móvil desarrollada en Android/Kotlin que permite jugar el clásico juego de Tres en Línea entre dos jugadores. Incluye detección de ganador, empate, reinicio de partida y sistema de puntuación.

## 🧩 Características principales

- ✖️ Juego local para dos jugadores  
- 🧠 Detección automática de ganador y empate  
- 🔄 Reinicio de partida sin cerrar la app  
- 📊 Puntuación acumulada por jugador  
- 🎨 Interfaz responsiva con cambio de color según turno

## 🛠️ Tecnologías utilizadas

- Lenguaje: `Kotlin`  
- UI: `XML`  
- IDE: `Android Studio`  
- Arquitectura: `Activity` principal con lógica modular

## 📂 Estructura del proyecto

```plaintext
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/x_0/GameActivity.kt
│   │       └── res/layout/activity_game.xml
├── README.md
```

## ⚙️ Lógica del juego

- El tablero está compuesto por 9 botones (`bt1` a `bt9`)  
- Cada jugador alterna entre "X" y "O"  
- Se valida el ganador con la función `validateCards()`  
- Se detecta empate con `validateDraw()`  
- Se actualiza la puntuación y se muestra un `Toast` con el resultado  
- La función `nuevapartida()` limpia el tablero y alterna el turno inicial

## 🧪 Ejemplo de flujo

1. El jugador 1 inicia con "X"  
2. Tras cada jugada se valida si hay ganador o empate  
3. Si hay ganador, se actualiza el marcador y se bloquea el tablero  
4. Si hay empate, se muestra mensaje y se permite reiniciar  
5. Al presionar "Nueva partida", se limpia el tablero y se alterna el turno inicial

## 📌 Próximas mejoras sugeridas

- Modo contra IA  
- Animaciones de victoria  
- Estadísticas de partidas  
- Sonidos y efectos visuales

## 📄 Licencia

Este proyecto está bajo la licencia MIT. Puedes usarlo, modificarlo y distribuirlo libremente.

---
