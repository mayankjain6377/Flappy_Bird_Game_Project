# 🐦 Flappy Bird Android Game

A simple yet addictive Flappy Bird clone built with **Java** in **Android Studio**.

![Flappy Bird Game]([https://upload.wikimedia.org/wikipedia/en/0/0a/Flappy_Bird_screenshot.png](https://imgs.search.brave.com/zr-ZN1UWpDr6YMcbnFK1jx-jHt2IYKgw6Kufn7nYnlI/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93d3cu/cG5nYWxsLmNvbS93/cC1jb250ZW50L3Vw/bG9hZHMvMTUvRmxh/cHB5LUJpcmQtUE5H/LVBpY3R1cmUucG5n))

---

## 📚 Table of Contents

- [🔍 Overview](#-overview)
- [🗂 Project Structure](#-project-structure)
- [🎮 Game Features](#-game-features)
- [🕹️ How to Play](#-how-to-play)
- [⚙️ Installation](#-installation)
- [🧠 Implementation Details](#-implementation-details)
- [🙏 Credits](#-credits)
- [📝 License](#-license)

---

## 🔍 Overview

An Android version of the iconic **Flappy Bird** game where players navigate a bird through a series of pipes. The gameplay becomes more challenging as your score increases. Built to mimic the mechanics of the original game with added enhancements.

---

## 🗂 Project Structure


## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/flappybird/
│   │   │   ├── MainActivity.java         # Entry point of the application
│   │   │   ├── GameView.java             # Custom view for game rendering
│   │   │   ├── Bird.java                 # Bird entity and physics
│   │   │   ├── Pipe.java                 # Pipe generation and movement
│   │   │   ├── GameManager.java          # Game state management
│   │   │   ├── SoundManager.java         # Sound effects management
│   │   │   └── ScoreManager.java         # Score tracking
│   │   ├── res/
│   │   │   ├── drawable/
│   │   │   │   ├── bird.png              # Bird sprite
│   │   │   │   ├── background.png        # Game background
│   │   │   │   ├── pipe_top.png          # Top pipe sprite
│   │   │   │   ├── pipe_bottom.png       # Bottom pipe sprite
│   │   │   │   └── ground.png            # Ground sprite
│   │   │   ├── raw/
│   │   │   │   ├── flap.wav              # Wing flap sound
│   │   │   │   ├── score.wav             # Point scoring sound
│   │   │   │   └── hit.wav               # Collision sound
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml     # Main activity layout
│   │   │   │   ├── game_over_dialog.xml  # Game over dialog layout
│   │   │   │   └── main_menu.xml         # Main menu layout
│   │   │   ├── values/
│   │   │   │   ├── strings.xml           # String resources
│   │   │   │   ├── colors.xml            # Color resources
│   │   │   │   └── styles.xml            # Style resources
│   │   │   └── mipmap/
│   │   │       └── ic_launcher.png       # App icon
│   │   └── AndroidManifest.xml           # App manifest
├── build.gradle                          # Project build file
└── README.md                             # This file
```

## Game Features

### Core Mechanics
- **Bird Physics**: Realistic gravity and flapping mechanics
- **Pipe Generation**: Randomly generated pipe heights and positions
- **Collision Detection**: Precise collision detection between bird and pipes/ground
- **Score Tracking**: Points increment when successfully passing through pipes
- **High Score**: SharedPreferences storage of high scores

### Visual Elements
- **Custom View Drawing**: Game rendering using Canvas and Paint
- **Sprite Animations**: Bird flapping animation
- **Visual Feedback**: Screen flash on collision
- **Day/Night Cycle**: Background changes based on score

### Audio Elements
- **Sound Effects**: Wing flap, scoring, and collision sounds
- **Background Music**: Optional game music that can be toggled

## How to Play

1. Tap the screen to make the bird flap its wings and fly upward
2. Navigate through the gaps between pipes
3. Each successfully passed pipe pair earns you one point
4. The game ends when the bird hits a pipe or the ground
5. Try to beat your high score!

## Installation

### Development Setup
1. Clone this repository:
   ```
   git clone https://github.com/yourusername/flappy-bird-android.git
   ```
2. Open the project in Android Studio
3. Build and run the app on an emulator or physical device

### APK Installation
1. Download the APK from the releases section
2. Enable installation from unknown sources in your device settings
3. Install and run the game

## Implementation Details

### MainActivity.java
The main entry point that handles:
- Activity lifecycle management
- UI initialization
- Navigation between menus and game

### GameView.java
Custom View that handles:
- Game loop using a dedicated thread
- Canvas drawing and rendering
- Touch event handling
- Game state management

### Bird.java
Handles bird-related functionality:
- Physics simulation (gravity, velocity, acceleration)
- Flapping mechanics
- Rotation based on vertical velocity
- Sprite animation

### Pipe.java
Manages pipe generation and behavior:
- Random height generation within playable bounds
- Pipe movement and recycling
- Speed adjustments based on score

### GameManager.java
Implements game logic:
- Collision detection algorithms
- Game state transitions
- Difficulty progression

### ScoreManager.java
Handles scoring system:
- Score increment logic
- High score tracking with SharedPreferences
- Visual score display

### SoundManager.java
Manages game audio:
- Sound effects using SoundPool
- Background music using MediaPlayer
- Audio focus handling

## Credits

- Original Flappy Bird game by Dong Nguyen
- Graphics inspired by the original game
- Sound effects from [freesound.org](https://freesound.org)

## License

This project is licensed under the MIT License - see the LICENSE file for details.
