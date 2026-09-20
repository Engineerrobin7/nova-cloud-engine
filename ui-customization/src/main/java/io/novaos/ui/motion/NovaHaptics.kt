package io.novaos.ui.motion

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.os.Build

/**
 * NovaHaptics: Low-latency haptic synchronization for micro-interactions.
 */
object NovaHaptics {

    /**
     * Triggers a discrete haptic tick. Best used for toggle switches and button clicks.
     */
    fun performTick(context: Context) {
        val vibrator = getVibrator(context)
        vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK))
    }

    /**
     * Triggers a heavy "click" sensation. Best used for app launches or long-press.
     */
    fun performHeavyClick(context: Context) {
        val vibrator = getVibrator(context)
        vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK))
    }

    /**
     * Performs a custom vibration effect synced with animation progress thresholds.
     */
    fun performBoundaryHaptic(context: Context) {
        val vibrator = getVibrator(context)
        vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK))
    }

    private fun getVibrator(context: Context): Vibrator {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val manager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            manager.defaultVibrator
        } else {
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
    }
}
