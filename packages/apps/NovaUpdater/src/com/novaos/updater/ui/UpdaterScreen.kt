package com.novaos.updater.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.novaos.core.ota.NovaOtaManager

@Composable
fun UpdaterScreen(
    status: NovaOtaManager.OtaStatus,
    progress: Float,
    onCheckUpdate: () -> Unit,
    onReboot: () -> Unit
) {
    val animatedProgress by animateFloatAsState(targetValue = progress)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "NovaOS System Update",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(48.dp))

        // Progress Pill
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color.DarkGray)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(animatedProgress)
                    .fillMaxHeight()
                    .background(Color(0xFF007FAC))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = when (status) {
                is NovaOtaManager.OtaStatus.Idle -> "Your system is up to date"
                is NovaOtaManager.OtaStatus.Verifying -> "Verifying system integrity..."
                is NovaOtaManager.OtaStatus.Applying -> "Applying update (${(progress * 100).toInt()}%)"
                is NovaOtaManager.OtaStatus.ReadyToReboot -> "Update complete. Reboot to finish."
                is NovaOtaManager.OtaStatus.Error -> "Error: ${status.message}"
            },
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(64.dp))

        if (status is NovaOtaManager.OtaStatus.ReadyToReboot) {
            Button(
                onClick = onReboot,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.fillMaxWidth().height(56.dp)
            ) {
                Text("Reboot Now", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        } else if (status is NovaOtaManager.OtaStatus.Idle || status is NovaOtaManager.OtaStatus.Error) {
            OutlinedButton(
                onClick = onCheckUpdate,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.fillMaxWidth().height(56.dp)
            ) {
                Text("Check for Updates", color = Color.White)
            }
        }
    }
}
