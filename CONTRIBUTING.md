# Contributing to NovaOS

We welcome contributions that align with our core principles of **Privacy, Speed, and Zero Bloat**.

## 1. Guiding Principles for Contributors
*   **Privacy First**: Before adding a feature, ask: "Can this work offline?" If yes, it must.
*   **Zero Bloat**: Every line of code should have a purpose. Avoid adding unnecessary dependencies.
*   **No Dark Patterns**: UI changes should be honest. No "nagging" popups or hidden toggles.

## 2. Code Standards
*   Follow the AOSP Java/Kotlin style guides.
*   Ensure all new apps are marked as uninstallable in their respective product makefiles.
*   New system services must be accompanied by appropriate SEPolicy entries.

## 3. How to Submit
1.  Fork the relevant repository (e.g., `packages/apps/NovaLauncher`).
2.  Create a feature branch.
3.  Submit a Pull Request with a clear explanation of how it improves the user experience without violating our principles.

## 4. Reporting Issues
Use the `BUGS.md` file in the root directory to track known issues. For security-related bugs, please email security@infynova.com directly.
