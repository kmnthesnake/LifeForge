# LifeForge

A production-quality Android life simulation game built with Kotlin, Jetpack Compose, and Material 3.

## Features

- **Character Creation**: Generate characters with randomized stats
- **Gameplay Mechanics**: Age your character, trigger random events
- **Stats System**: Health, Fitness, Looks, Intelligence
- **Save/Load System**: Persistent character data using Room database
- **Dark Mode Support**: Automatic light/dark theme switching
- **Material 3 UI**: Modern, beautiful Material Design 3 interface

## Architecture

- **Clean Architecture**: Separation of concerns with domain, data, and presentation layers
- **MVVM Pattern**: ViewModel-based state management
- **Repository Pattern**: Abstraction for data sources
- **Hilt Dependency Injection**: Automated dependency management

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Design System**: Material 3
- **Database**: Room
- **Dependency Injection**: Hilt
- **Minimum SDK**: 29

## Building

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run the app

## Project Structure

```
app/src/main/
├── kotlin/com/lifeforge/app/
│   ├── data/
│   │   ├── local/
│   │   ├── mapper/
│   │   ├── repository/
│   │   └── di/
│   ├── domain/
│   │   ├── model/
│   │   └── usecase/
│   ├── presentation/
│   │   ├── navigation/
│   │   ├── theme/
│   │   ├── ui/
│   │   │   ├── screen/
│   │   │   └── component/
│   │   └── viewmodel/
│   └── MainActivity.kt
├── res/
│   ├── values/
│   └── xml/
└── AndroidManifest.xml
```

## License

MIT License - See LICENSE file for details
