package app.allulith.home.api.destinations

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class HomeDestination : NavKey {
    @Serializable
    data object Home : HomeDestination()
}
