package io.novaos.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * DynamicCapsule: A top-level status bar replacement that shows active system context.
 */
@Composable
fun DynamicCapsule(
    batteryLevel: Int,
    networkSpeed: String,
    activeSensors: List<String>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(32.dp))
                .background(Color.DarkGray.copy(alpha = 0.9f))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CapsuleItem("BAT $batteryLevel%")
            CapsuleItem(networkSpeed)
            
            if (activeSensors.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.Green)
                )
            }
        }
    }
}

@Composable
private fun CapsuleItem(text: String) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 11.sp,
        style = MaterialTheme.typography.labelMedium
    )
}
