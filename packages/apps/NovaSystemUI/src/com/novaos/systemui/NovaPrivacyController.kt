package com.novaos.systemui

import android.content.Context
import android.view.View
import androidx.compose.ui.platform.ComposeView
import io.novaos.core.privacy.ui.PrivacyPill
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NovaPrivacyController @Inject constructor() {

    fun createPrivacyView(context: Context): View {
        return ComposeView(context).apply {
            setContent {
                PrivacyPill(
                    isCameraActive = true, // Mocked, will connect to system listeners
                    isMicActive = false,
                    onRevokeClick = { 
                        // Implementation for revocation logic 
                    }
                )
            }
        }
    }
}
