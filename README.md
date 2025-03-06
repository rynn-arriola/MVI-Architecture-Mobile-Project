# 🚀 Android MVI App

An Android application built using 
**MVI architecture, Jetpack Compose, Kotlin Flow, and Dagger Hilt**. 
This project follows a modern approach to Android development, ensuring 
**scalability, maintainability, and a smooth user experience**.

---

## 📱 Features
✅ **MVI (Model-View-Intent) Architecture** for unidirectional data flow.  
✅ **Dagger Hilt** for dependency injection.  
✅ **Jetpack Compose** for modern UI development.  
✅ **Retrofit + Gson** for network API calls.  
✅ **Kotlin Flow + StateFlow** for reactive state management.  
✅ **Coil Compose** for efficient image loading.
✅ **MockK for unit testing** with coroutine support.

---

## 🛠 Tech Stack

| Tech                          | Purpose                                         |
|-------------------------------|-------------------------------------------------|
| 🛠 **Kotlin**                 | Primary programming language                    |
| 🏗 **Jetpack Compose**        | Declarative UI framework                        |
| 🔥 **MVI Architecture**       | Ensures predictable state management            |
| ⚡ **Kotlin Flow & StateFlow** | Handles asynchronous data streams               |
| 🔧 **Dagger Hilt**            | Dependency injection                            |
| 🌐 **Retrofit & Gson**        | API requests and JSON parsing                   |
| 🖼 **Coil Compose**           | Efficient image loading                         |
| 🖼 **Mockk**                  | Unit testing framework for mocking dependencies |

---

```
📂 rynnarriola
├── 📂 mobileproject_mvi
│   ├── 📂 di           # 🔧 Dependency Injection (Dagger Hilt Modules)
│   │   └── AppModule.kt
│   │
│   ├── 📂 model        # 📦 Data Models & API Services
│   │   ├── Employee.kt
│   │   ├── EmployeeApi.kt
│   │   └── EmployeeRepo.kt
│   │
│   ├── 📂 ui           # 🎨 Jetpack Compose Screens
│   │   └── EmployeeScreen.kt
│   │
│   ├── 📂 util         # ⚙️ Utilities & Intent Management
│   │   ├── EmployeeIntent.kt
│   │   └── UiState.kt
│   │
│   ├── 📂 viewmodel    # 🧠 ViewModel (StateFlow + MVI)
│   │   └── EmployeeViewModel.kt
│   │
│   ├── MainActivity.kt   # 🚀 Entry Point of the App
│   └── MyApplication.kt  # 🌎 Application Class (Hilt Setup)
```