# NovaOS Architecture

NovaOS is an AOSP-based operating system with a modular architecture focused on privacy and on-device intelligence.

## 1. System Components

### NovaAIService (`com.novaos.ai`)
The core intelligence engine. It provides on-device NLP, translation, and system optimization. It is implemented as a system service with restricted access.

### NovaGuard (`com.novaos.guard`)
A security layer that monitors system integrity and provides high-level APIs for secure data management (e.g., Privacy Vault, App Lock).

### NovaThemeService
Manages dynamic overlays and system-wide aesthetics suggested by the AI or selected by the user.

## 2. Application Layer

### NovaLauncher
A minimalist home screen that integrates the "Nova Line" (AI search/command bar) and respects user privacy by hiding protected apps.

### NovaSettings
A unified settings app that integrates standard AOSP settings with Nova-specific features like Intelligence, Privacy Lab, and Device Care.

### NovaPrivacyCenter
A central hub for users to manage permissions, track data usage, and control background network access.

### NovaDeviceCare
A system utility for monitoring battery health, managing storage, and optimizing performance without aggressive "cleaner" tactics.

## 3. Customization Layer (Overlays)
NovaOS uses Runtime Resource Overlays (RROs) to apply branding and themes.
- `vendor/infynova/overlay`: Common branding (colors, strings).
- `vendor/infynova/overlay-systemui`: SystemUI-specific customizations.
- `vendor/infynova/overlay-settings`: Settings-specific customizations.
