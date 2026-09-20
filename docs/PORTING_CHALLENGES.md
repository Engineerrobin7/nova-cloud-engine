# GSI Porting Challenges & Fixes

Building a Universal GSI comes with unique challenges due to the diversity of Android hardware.

## Known Issues

### 1. Brightness Slider
On some devices, the brightness slider doesn't work out-of-the-box.
- **Fix**: Use the `persist.sys.qcom.force_brightness_config` property or apply the `backlight.scale` fix in `nova_gsi.mk`.

### 2. VoLTE / IMS
Carrier-specific features are notoriously difficult on GSIs.
- **Fix**: NovaOS includes the `IMS-Resolver` package, but you may need to manually trigger the IMS setup from the Hidden Menu (`*#*#4636#*#*`).

### 3. Fingerprint Sensor (FOD)
In-display fingerprint sensors often require specific HAL patches not present in a generic system image.
- **Status**: Experimental. High-end Pixel/OnePlus GSIs may work, but Xiaomi/Samsung implementations often vary.

### 4. Image Size (Model Weights)
The standard NovaOS GSI is ~2GB larger than stock GSIs due to the local AI model.
- **Warning**: Ensure your `/system` partition is large enough (Dynamic Partitions are recommended).
