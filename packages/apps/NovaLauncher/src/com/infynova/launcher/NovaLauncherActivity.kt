package com.infynova.launcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.util.Log
import io.novaos.ui.theme.NovaOSTheme
import com.infynova.launcher.NovaHomeScreen

/**
 * NovaLauncherActivity: The entry point for the NovaOS MVP Launcher.
 * Hosts the Compose-based home screen.
 */
class NovaLauncherActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Priority Render Pipeline for MVP
        System.setProperty("debug.nova.render_priority", "1")

        setContent {
            NovaOSTheme {
                NovaHomeScreen(
                    onSwipeUp = { Log.d("NovaLauncher", "Swipe Up: App Drawer") },
                    onSwipeDown = { Log.d("NovaLauncher", "Swipe Down: Quick Toggles") }
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Ensure smooth transitions
        System.setProperty("debug.nova.render_priority", "1")
    }

    override fun onPause() {
        super.onPause()
        System.setProperty("debug.nova.render_priority", "0")
    }
}
