package io.novaos.core.security.telemetry

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * NovaLocalTelemetry: Stores performance and usage data strictly on-device.
 * Stored in an encrypted database or shared preferences.
 */
@Singleton
class NovaLocalTelemetry @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPrefs = EncryptedSharedPreferences.create(
        context,
        "nova_local_telemetry",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    /**
     * Logs a local event. Never transmitted to a server.
     */
    fun logEvent(event: String, value: String) {
        val timestamp = System.currentTimeMillis()
        sharedPrefs.edit().putString("${event}_$timestamp", value).apply()
    }

    /**
     * Retrieves local logs for debugging purposes by the user.
     */
    fun getLocalLogs(): Map<String, *> {
        return sharedPrefs.all
    }

    fun clearLogs() {
        sharedPrefs.edit().clear().apply()
    }
}
