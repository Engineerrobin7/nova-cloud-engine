package io.novaos.core.privacy

import android.location.Location
import android.provider.ContactsContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

/**
 * NovaPrivacyManager: The synthetic data spoofing layer.
 * Intercepts requests for sensitive data and provides randomized payloads when Spoof Mode is active.
 */
@Singleton
class NovaPrivacyManager @Inject constructor() {

    private val _isSpoofModeEnabled = MutableStateFlow(false)
    val isSpoofModeEnabled = _isSpoofModeEnabled.asStateFlow()

    fun toggleSpoofMode(enabled: Boolean) {
        _isSpoofModeEnabled.value = enabled
    }

    /**
     * Returns a spoofed location if Spoof Mode is enabled; otherwise null.
     */
    fun getSpoofedLocation(originalLocation: Location?): Location? {
        if (!_isSpoofModeEnabled.value) return originalLocation

        return Location("network").apply {
            latitude = Random.nextDouble(-90.0, 90.0)
            longitude = Random.nextDouble(-180.0, 180.0)
            accuracy = 100f
            time = System.currentTimeMillis()
        }
    }

    /**
     * Returns synthetic contact data for apps that shouldn't see the real contact list.
     */
    fun getSyntheticContacts(): List<Map<String, String>> {
        return listOf(
            mapOf("name" to "John Doe (Synthetic)", "phone" to "555-0199"),
            mapOf("name" to "Jane Smith (Synthetic)", "phone" to "555-0123")
        )
    }

    /**
     * Logic to decide if an app's request should be spoofed.
     * In a full AOSP implementation, this would hook into AppOpsManager.
     */
    fun shouldSpoofForPackage(packageName: String): Boolean {
        // Implement allow-list / block-list logic here
        return _isSpoofModeEnabled.value
    }
}
