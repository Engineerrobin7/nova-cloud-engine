package io.novaos.core.privacy.ui

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * PrivacyPill: A persistent UI element that shows active sensor usage.
 */
@Composable
fun PrivacyPill(
    isCameraActive: Boolean,
    isMicActive: Boolean,
    onRevokeClick: () -> Unit
) {
    AnimatedVisibility(
        visible = isCameraActive || isMicActive,
        enter = fadeIn() + expandHorizontally(),
        exit = fadeOut() + shrinkHorizontally()
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.Black.copy(alpha = 0.8f))
                .clickable { onRevokeClick() }
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (isCameraActive) {
                PrivacyIcon(Icons.Default.CameraAlt, Color.Green)
            }
            if (isMicActive) {
                PrivacyIcon(Icons.Default.Mic, Color.Yellow)
            }
            
            Text(
                text = "Tap to Revoke",
                color = Color.White,
                fontSize = 12.sp,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
private fun PrivacyIcon(icon: ImageVector, tint: Color) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = tint,
        modifier = Modifier.size(16.dp)
    )
}
