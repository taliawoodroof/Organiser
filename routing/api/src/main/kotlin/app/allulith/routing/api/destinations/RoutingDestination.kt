package app.allulith.routing.api.destinations

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class RoutingDestination : NavKey {
    @Serializable
    data object Routing : RoutingDestination()
}
