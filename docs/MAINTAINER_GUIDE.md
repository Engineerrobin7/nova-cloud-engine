# NovaOS Maintainer Guide

Welcome to the NovaOS project. This guide outlines how to provide "Official" support for a specific device.

## Prerequisites
- A working AOSP-based device tree for your device.
- Basic knowledge of Git and the Android build system.

## Integration Steps

### 1. Inherit NovaOS Features
In your `device.mk` or `product.mk`, add the following line after common AOSP inherits:
```makefile
$(call inherit-product, vendor/infynova/config/common.mk)
```

### 2. Branding Overrides
Ensure your `PRODUCT_BRAND` and `PRODUCT_MODEL` are set correctly, but keep `PRODUCT_MANUFACTURER` if needed. NovaOS will automatically apply its branding overlays.

### 3. SEPolicy
Include the NovaOS common sepolicy:
```makefile
BOARD_VENDOR_SEPOLICY_DIRS += vendor/infynova/sepolicy/common
```

## Official Build Criteria
To be considered for "Official" status:
- All core features (Dynamic Island, AI Hub, Privacy Guardian) must be functional.
- The build must pass SafetyNet/Play Integrity out-of-the-box.
- You must commit to regular monthly updates aligned with AOSP security patches.

## Submission
Create a Pull Request on the main NovaOS GitHub organization with your device tree and a screenshot of the "About Phone" screen showing the NovaOS version.
