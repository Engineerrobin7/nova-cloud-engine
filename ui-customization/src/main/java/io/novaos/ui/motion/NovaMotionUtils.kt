package io.novaos.ui.motion

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

/**
 * NovaMotionUtils: Performance-first animation utilities.
 * Ensures that all transformations are applied directly to the RenderNode (Graphics Layer).
 */
object NovaMotionUtils {

    /**
     * Applies optimized spatial transformations without triggering re-layout.
     */
    fun Modifier.novaTransform(
        scale: Float = 1f,
        translationX: Float = 0f,
        translationY: Float = 0f,
        alpha: Float = 1f,
        rotationZ: Float = 0f
    ): Modifier = this.graphicsLayer {
        this.scaleX = scale
        this.scaleY = scale
        this.translationX = translationX
        this.translationY = translationY
        this.alpha = alpha
        this.rotationZ = rotationZ
        
        // Force hardware acceleration and clipping optimization
        this.clip = true
    }
}
