# 🎮 NextLVL Gaming — Android Application

![NextLVL Gaming Logo](app/src/main/res/drawable/nextlvl.png)

An elite, high-performance Android e-commerce and gaming experience application built for **NextLVL Gaming** (Established 2023, Founder & Owner **Jason Naidoo**). Designed with modern Jetpack components, Kotlin, ViewBinding, custom glassmorphic UI elements, dynamic calculations, and strict client-side data validation.

---

## 🌟 Key Features

- **⚡ Phase-In Opening Splash Screen**:
  - Pure pitch-black launch screen featuring a smooth phase-in logo animation and Solar Lime headline typography (`Enraged Yetti`).
- **☰ Slide-Out Hamburger Navigation Drawer**:
  - Instant access to all available app destinations (`Home`, `About Us`, `Products & Packages`, `Shopping Cart`, `Secure Checkout`, `Sign In / Register`, `My Account`, `General Enquiry`, `Contact Us`).
- **💎 Glassmorphic Design System (Liquid Glass)**:
  - Deep obsidian dark background (`#0B0E14`), glowing neon green accents (`#00FF66`), focus-animated input selectors, and rounded glass card containers.
- **🛒 Dynamic Cart & Checkout Engine**:
  - Live subtotal, tiered volume discounts (5%, 10%, 15%), transaction fees (`R20 + 2.5%`), and grand total calculations.
  - No static placeholders — checkout basket dynamically renders the user's selected items in real time.
- **💳 Multi-Payment Method Validation**:
  - **Capitec Pay**: Validates registered 10-digit mobile number & full name.
  - **Ozow Instant EFT**: Validates 10-digit mobile number & notification email.
  - **Credit / Debit Card**: Validates 16-digit card number, MM/YY expiry, 3-digit CVV, and cardholder name.
- **📧 Interactive Email Notifications (`dior.dynasty54@gmail.com`)**:
  - Triggers interactive email intents upon successful registration or order checkout: *"Welcome to NextLVL Gaming! We are so glad to have a fellow Yetti join our tribe!"*.
- **📞 Interactive Native App Integration**:
  - Tap-to-call phone dialer (`tel:+27123456789`), email launcher (`mailto:hello@nextlvlgaming.co.za`), and Google Maps location launcher for **Gamer HQ** (`42 Neon Lane, Sandton, Johannesburg`).
- **👤 User Session & Fresh Profile State**:
  - Clean user session management for new users without prebuilt static data. Fresh order history and ticket tracking.

---

## 🏢 About NextLVL Gaming

- **Established**: 2023
- **Founder & Owner**: Jason Naidoo
- **HQ Location**: 42 Neon Lane, Sandton, Johannesburg, South Africa
- **Copyright**: © 2026 NextLVL GAMING. All rights reserved.

---

## 🛠️ Technology Stack

- **Language**: Kotlin 1.9+
- **Min SDK**: API Level 24 (Android 7.0)
- **Target SDK**: API Level 36 (Android 15)
- **Architecture**: Single-Activity, Multi-Fragment Architecture with AndroidX Navigation Component
- **UI Components**:
  - Material Design 3 (`com.google.android.material`)
  - ViewBinding (`ActivityMainBinding`, `FragmentHomeBinding`, etc.)
  - ConstraintLayout & CoordinatorLayout with `AppBarLayout`
  - DrawerLayout & NavigationView
  - RecyclerView & CardView
- **Build System**: Gradle with Kotlin DSL (`build.gradle.kts`)

---

## 📂 Project Structure

```
Enragedyetti/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/enragedyetti/
│   │   │   │   ├── MainActivity.kt               # Main activity, splash animation & drawer setup
│   │   │   │   ├── HomeFragment.kt               # Home screen with "On the Horizon" carousel
│   │   │   │   ├── ProductsFragment.kt           # Products & experiences catalog
│   │   │   │   ├── CartFragment.kt               # Shopping cart & live summary
│   │   │   │   ├── CartAdapter.kt                # RecyclerView adapter for cart items
│   │   │   │   ├── CartManager.kt                # Cart state management & discount calculations
│   │   │   │   ├── CheckoutFragment.kt           # Dynamic payment method form & checkout
│   │   │   │   ├── PaymentSuccessfulFragment.kt  # Order confirmation screen
│   │   │   │   ├── SignInFragment.kt             # Sign in, sign up & contact toggle
│   │   │   │   ├── ProfileFragment.kt            # Member profile, orders, password & tickets
│   │   │   │   ├── AboutFragment.kt              # Company history, vision & owner info
│   │   │   │   ├── EnquiryFragment.kt            # General enquiry form & dropdowns
│   │   │   │   ├── ContactFragment.kt            # Contact details & native app intents
│   │   │   │   └── UserSessionManager.kt         # User account session & email intent handlers
│   │   │   ├── res/
│   │   │   │   ├── drawable/                     # Custom glassmorphic drawables & vector icons
│   │   │   │   ├── layout/                       # XML layout files for screens & components
│   │   │   │   ├── menu/                         # Drawer navigation menu & action bar menu
│   │   │   │   ├── font/                         # Custom typography definitions
│   │   │   │   └── values/                       # Colors, themes, and strings
│   │   │   └── AndroidManifest.xml              # Application configuration & permissions
│   └── build.gradle.kts                          # Module-level Gradle configuration
├── build.gradle.kts                              # Project-level Gradle configuration
├── settings.gradle.kts                           # Gradle settings
└── README.md                                     # Project documentation
```

---

## 🚀 Setup & Installation Instructions

1. **Clone Repository**:
   ```bash
   git clone https://github.com/J5Molapo/NextLVL_Gaming-APP.git
   ```
2. **Open in Android Studio**:
   - Open Android Studio (Ladybug 2024.2.1 or newer).
   - Select **Open an existing project** and choose the cloned folder.
3. **Build & Sync**:
   - Allow Gradle sync to complete automatically.
   - Run Gradle assemble: `./gradlew app:assembleDebug` or click **Sync Project with Gradle Files**.
4. **Run Application**:
   - Connect an Android device via USB with debugging enabled, or start an Android Virtual Device (AVD).
   - Click **Run 'app'** (`Shift + F10`).

---

## 📄 License

© 2026 NextLVL GAMING. All rights reserved.  
Developed for **NextLVL Gaming** — Founder & Owner **Jason Naidoo**.
