## NovaOS Master Checklist

### 1. AOSP & Device Setup
- [ ] Choose primary reference device (Pixel / OnePlus / dev board)
- [ ] Set up Linux build environment
- [ ] Sync AOSP source
- [ ] `source build/envsetup.sh` works [source.android](https://source.android.com/docs/setup/build/building)
- [ ] `lunch <device>-userdebug` works [source.android](https://source.android.com/docs/setup/build/building)
- [ ] Full build completes (`m` or `make`) [source.android](https://source.android.com/docs/setup/build/building)
- [ ] Create `device/<vendor>/<device>/` tree
- [ ] Create `vendor/infynova/` vendor tree
- [ ] Define `nova_<device>-userdebug` lunch target
- [ ] Define `nova_<device>-user` lunch target
- [ ] Script basic build command (build.sh)

### 2. Core Philosophy & Product
- [ ] Write 3–5 non-negotiable rules:
  - [ ] No ads anywhere in system
  - [ ] All third-party apps uninstallable
  - [ ] No auto-install “suggested/hot apps”
  - [ ] Privacy safest by default
- [ ] Write one-line product promise
- [ ] Define target user persona
- [ ] List top 10 smartphone pain points from real users (bloat, ads, clutter) [androidauthority](https://www.androidauthority.com/fighting-bloatware-2025-3505475/)
- [ ] Map which pain points NovaOS will solve v1

### 3. Branding & Visual Identity
- [ ] Design NovaOS logo
- [ ] Choose primary font and fallbacks
- [ ] Define color palette (primary, accent, neutrals)
- [ ] Create default light wallpaper
- [ ] Create default dark wallpaper
- [ ] Create boot animation with NovaOS logo
- [ ] Implement boot animation in build
- [ ] Create icon guidelines (shape, style)
- [ ] Implement theme via overlay (colors.xml, styles.xml) [linkedin](https://www.linkedin.com/pulse/mastering-aosp-customization-device-overlays-guide-vikash-choudhary-j0w6f)

### 4. System UI (SystemUI app)
- [ ] Clone SystemUI for reference
- [ ] Clean status bar icons (minimal default set)
- [ ] Simplify quick settings tiles
- [ ] Add tile: Nova AI
- [ ] Add tile: Privacy mode
- [ ] Add tile: Performance mode
- [ ] Adjust notification shade layout (reduce clutter)
- [ ] Add visual indicators for mic/camera/location use
- [ ] Test orientation, notch/punch-hole layouts

### 5. Home Screen / Launcher
- [ ] Decide: use Launcher3 fork vs custom launcher
- [ ] Remove any “hot apps” / promoted folders [androidauthority](https://www.androidauthority.com/fighting-bloatware-2025-3505475/)
- [ ] Implement basic home grid (configurable)
- [ ] Implement app drawer with search
- [ ] Implement long-press actions (uninstall, app info)
- [ ] Default home: no third-party widgets
- [ ] Add NovaOS-branded wallpaper as default
- [ ] Test app install/uninstall flow from drawer

### 6. Settings App
- [ ] Reorganize main categories (Connectivity, Display, Sound, Privacy, Nova, System)
- [ ] Add “Nova Privacy & Security” section
- [ ] Add “Nova AI & Intelligence” section
- [ ] Add “Nova Device Care” section
- [ ] Remove any promotional banners
- [ ] Simplify wording of key settings
- [ ] Ensure search in settings works for new items

### 7. Essential System Apps
- [ ] Phone app (dialer)
- [ ] SMS app
- [ ] Contacts
- [ ] Camera
- [ ] Gallery
- [ ] Browser
- [ ] File manager
- [ ] Ensure: no ads, no forced logins in any defaults
- [ ] Ensure: all non-essential third-party apps are remov­able

### 8. NovaPrivacyCenter (App)
- [ ] Create `packages/apps/NovaPrivacyCenter/`
- [ ] Screen: list apps with permissions
- [ ] Screen: internet access per app
- [ ] Screen: mic/camera/location usage overview
- [ ] Action: open app details / revoke permissions
- [ ] Action: shortcut to disable background activity
- [ ] Add shortcut in Settings → Privacy
- [ ] Add launcher icon (optional)
- [ ] Add Quick Settings shortcut to open PrivacyCenter

### 9. NovaAI (App / Service)
- [ ] Create `packages/apps/NovaAI/`
- [ ] Implement offline command handler (open app, toggle Wi‑Fi, etc.)
- [ ] Implement simple text actions (summarize, quick reply MVP)
- [ ] Create minimal UI (chat-like screen)
- [ ] Add Quick Settings tile for NovaAI
- [ ] Add long-press power button or gesture hook (optional, later)
- [ ] Ensure: on-device by default, no cloud dependency

### 10. NovaDeviceCare
- [ ] Create `packages/apps/NovaDeviceCare/`
- [ ] Battery page (apps by drain)
- [ ] Storage page (big files, unused apps)
- [ ] Performance profiles (Balanced / Performance / Battery saver)
- [ ] One-tap checkup (basic, no snake-oil “cleaner”)
- [ ] Integrate into Settings → Nova Device Care

### 11. NO Bloat Implementation
- [ ] Audit `PRODUCT_PACKAGES` for unnecessary apps
- [ ] Remove shopping, social, content partner apps by default
- [ ] Ensure no OEM-style app stores that push content [androidauthority](https://www.androidauthority.com/fighting-bloatware-2025-3505475/)
- [ ] Make all non-core third-party packages uninstallable
- [ ] Test clean install: check app list feels minimal

### 12. NO Ads Implementation
- [ ] Search codebase for ad SDKs and remove
- [ ] Ensure:
  - [ ] No lockscreen ads
  - [ ] No notification shade ads
  - [ ] No ads inside system apps
- [ ] No “Lock Glimpse” / “Glance” style layers [notebookcheck](https://www.notebookcheck.net/Nothing-Phones-to-get-advertising-and-bloatware.1147833.0.html)
- [ ] Verify: no background “recommendation” services run

### 13. Onboarding / Setup
- [ ] Simple setup wizard (language, Wi‑Fi, Google account)
- [ ] Add Nova privacy choices:
  - [ ] Minimal data
  - [ ] Standard
  - [ ] Custom
- [ ] Add optional “recommended apps” page, all toggles OFF by default
- [ ] Explain NovaOS values briefly on one screen
- [ ] Ensure: no forced sign-up for Nova account for basic usage

### 14. Notifications & Dark Patterns
- [ ] Default promotional notification categories OFF
- [ ] Clear notification channels for system apps
- [ ] No “rate us now” nagging loops
- [ ] No periodic “clean device / boost RAM” spam
- [ ] Make all suggestions dismissable and not auto-return

### 15. Privacy & Security Deep
- [ ] Implement privacy dashboard (system-level view)
- [ ] Mic/camera/location indicators in status bar
- [ ] Quick way to revoke recently used permissions
- [ ] Storage encryption enabled by default
- [ ] Strong lockscreen flows (PIN/pattern/password/biometric)
- [ ] Make lockscreen notifications privacy-aware (content hiding options)
- [ ] Document data handling philosophy (no hidden sync)

### 16. Performance & Battery
- [ ] Review running services; disable non-essential ones
- [ ] Tune animation scales and durations
- [ ] Tune LMK / memory settings for chosen SoC
- [ ] Perform battery test with typical day usage
- [ ] Optimize wakeups and alarms for Nova apps
- [ ] Benchmark vs stock build (boot time, app launch)

### 17. Stability & Bugfix Loop
- [ ] Enable useful log tags for debugging
- [ ] Create small internal “feedback” app or script to collect logs
- [ ] Test:
  - [ ] Calls, SMS
  - [ ] Camera (photo/video)
  - [ ] Wi‑Fi / mobile data
  - [ ] Bluetooth (audio, file)
  - [ ] Sensors (brightness, rotation)
- [ ] Maintain a BUGS.md with known issues
- [ ] Fix crashes from logs and user reports regularly

### 18. Documentation & Developer Experience
- [ ] `README.md` (what is NovaOS, how to build)
- [ ] `PRINCIPLES.md` (no ads, no bloat, privacy-first)
- [ ] `ARCHITECTURE.md` (apps and system layout)
- [ ] `FEATURES.md` (what’s different from stock)
- [ ] `ROADMAP.md` (0.1, 0.2, 0.3 goals)
- [ ] `CHANGELOG.md` (version history)
- [ ] `CONTRIBUTING.md` (how others should contribute)

### 19. Demo & Launch Prep
- [ ] Prepare demo script (boot → home → privacy → NovaAI → no ads)
- [ ] Record screen + physical camera demo
- [ ] Capture key screenshots
- [ ] Write brief product story for LinkedIn / YouTube
- [ ] Identify 5–10 early testers (privacy-focused, anti-bloat users) [discuss.privacyguides](https://discuss.privacyguides.net/t/whats-the-best-brand-or-specific-smartphone-devices-that-offer-the-best-privacy-and-least-bloatware/27642)
- [ ] Collect feedback and prioritize fixes

### 20. Business & Future
- [ ] List possible revenue streams (hardware margin, optional services)
- [ ] Explicitly reject ad-based and lockscreen monetization models [nothing](https://nothing.community/d/46831-nothings-new-trajectory)
- [ ] Outline future premium features that still respect user (e.g., optional Nova+ AI)
- [ ] Plan update policy (how long devices get NovaOS updates)
