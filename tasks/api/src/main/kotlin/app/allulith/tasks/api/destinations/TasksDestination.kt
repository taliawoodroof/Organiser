package app.allulith.tasks.api.destinations

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class TasksDestination : NavKey {

    @Serializable
    data class TaskCreation(val taskId: String?) : TasksDestination()
}
