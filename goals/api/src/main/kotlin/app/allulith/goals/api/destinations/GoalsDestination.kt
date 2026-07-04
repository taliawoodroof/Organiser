package app.allulith.goals.api.destinations

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class GoalsDestination : NavKey {
    @Serializable
    data object GoalsOverview : GoalsDestination()
}
