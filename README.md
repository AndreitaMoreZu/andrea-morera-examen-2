# Panini Ticket Support
---

## Description

The application centralizes support request management for Panini's FIFA World Cup 2026 album operations, replacing informal communication channels (emails, spreadsheets) with a structured ticketing system.

## Tech Stack

- **Kotlin** + **Jetpack Compose** (UI)
- **MVVM** + **Repository Pattern** (architecture)
- **StateFlow** (reactive event-based communication)
- **Navigation Compose** (screen navigation)
- **Retrofit** + **OkHttp** (networking layer, prepared for future backend)
- **Gson** (JSON serialization)
- **Mock data** (PoC — no backend required)

## Project Structure

```
/app          → Android Studio project (open this folder)
/contracts    → API Contracts in YAML (OpenAPI 3.0)
/docs         → Technical documentation and architectural decisions
/video        → Demo video link
```

## Running the App

1. Open Android Studio
2. Select **Open** and navigate to the `/app` folder
3. Wait for Gradle sync to complete
4. Run on emulator or physical device (minSdk 24 / Android 7.0+)

No backend configuration required — the app runs entirely on mock data.

## Key Features

- Simulated authentication
- Ticket list sorted by priority (Critical first) using LazyColumn
- Ticket detail with status update
- Create new ticket
- Feature Flags screen with two toggleable controls
- Automatic reactive updates via StateFlow — no manual screen reloads

## Architecture Notes

See `/docs/architecture.md` for full technical documentation including:
- Event-based communication flow
- Feature Flags implementation
- Networking layer structure
- Future evolution roadmap
