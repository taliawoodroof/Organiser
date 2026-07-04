package app.allulith.settings.api.destinations

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class SettingsDestination : NavKey {
    @Serializable
    data object Settings : SettingsDestination()
}
