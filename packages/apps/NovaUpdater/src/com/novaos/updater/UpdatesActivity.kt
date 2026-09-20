package com.novaos.updater

import android.os.Bundle
import android.os.PowerManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import io.novaos.core.ota.NovaOtaManager
import io.novaos.ui.theme.NovaOSTheme
import com.novaos.updater.ui.UpdaterScreen

class UpdatesActivity : ComponentActivity() {

    private val otaManager = NovaOtaManager.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            val status by otaManager.updateStatus.collectAsState()
            val progress by otaManager.progress.collectAsState()

            NovaOSTheme {
                UpdaterScreen(
                    status = status,
                    progress = progress,
                    onCheckUpdate = { 
                        // Simulated payload for MVP
                        otaManager.checkAndApplyUpdate("/data/ota_package.zip", arrayOf("header=value"))
                    },
                    onReboot = {
                        val pm = getSystemService(POWER_SERVICE) as PowerManager
                        pm.reboot(null)
                    }
                )
            }
        }
    }
}
