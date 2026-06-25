# LifeForge

A production-quality Android life simulation game built with Kotlin, Jetpack Compose, and Material 3.

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
- **Navigation**: Navigation Compose
- **Minimum SDK**: 29 (Android 10)
- **Target SDK**: 34 (Android 14)

## Project Structure

```
app/src/main/
├── kotlin/com/lifeforge/app/
│   ├── data/
│   │   ├── local/
│   │   ├── remote/
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
│   ├── MainActivity.kt
│   └── LifeForgeApplication.kt
├── res/
│   ├── values/
│   ├── values-night/
│   └── xml/
└── AndroidManifest.xml
```

## Building

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run the app on an emulator or device

## Features

### Milestone 1 - Architecture
- ✅ Complete Gradle configuration
- ✅ Material 3 theme with dark/light mode
- ✅ Navigation Compose setup
- ✅ Home screen with menu
- ✅ Placeholder screens for New Life, Continue, and Settings
- ✅ Clean Architecture structure
- ✅ MVVM pattern ready
- ✅ Hilt dependency injection configured

## Future Milestones

- Milestone 2: Character Creation System
- Milestone 3: Gameplay Mechanics
- Milestone 4: Save/Load System
- Milestone 5: Random Events System

## License

MIT License