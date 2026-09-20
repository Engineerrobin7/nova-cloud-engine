package io.novaos.ui.motion

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * NovaMotionEngine: Customizable physics engine for OS-wide animations.
 */
@Singleton
class NovaMotionEngine @Inject constructor() {

    private val _dampingRatio = MutableStateFlow(Spring.DampingRatioMediumLowBouncy)
    val dampingRatio = _dampingRatio.asStateFlow()

    private val _stiffness = MutableStateFlow(Spring.StiffnessMedium)
    val stiffness = _stiffness.asStateFlow()

    fun updateDamping(value: Float) {
        _dampingRatio.value = value
    }

    fun updateStiffness(value: Float) {
        _stiffness.value = value
    }

    /**
     * Returns a spring spec based on user preferences.
     */
    fun <T> getSpringSpec() = spring<T>(
        dampingRatio = _dampingRatio.value,
        stiffness = _stiffness.value
    )
}
