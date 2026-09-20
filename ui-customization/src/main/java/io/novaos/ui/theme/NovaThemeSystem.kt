package io.novaos.ui.theme

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sign

/**
 * SuperellipseShape: The "Squircle" masking used throughout NovaOS.
 * Based on the Lame curve formula: |x/a|^n + |y/b|^n = 1
 */
fun SuperellipseShape(n: Float = 3f) = GenericShape { size, _ ->
    val a = size.width / 2f
    val b = size.height / 2f
    
    moveTo(size.width, b)
    
    for (i in 0..360) {
        val angle = Math.toRadians(i.toDouble())
        val cos = Math.cos(angle)
        val sin = Math.sin(angle)
        
        val x = abs(cos).pow(2.0 / n) * a * sign(cos)
        val y = abs(sin).pow(2.0 / n) * b * sign(sin)
        
        lineTo((x + a).toFloat(), (y + b).toFloat())
    }
    close()
}

@Composable
fun NovaOSTheme(
    content: @Composable () -> Unit
) {
    // Dynamic color logic would go here (Monet integration)
    val colorScheme = darkColorScheme(
        primary = Color(0xFF007FAC),
        secondary = Color(0xFF00B2EE),
        background = Color(0xFF000000)
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}
