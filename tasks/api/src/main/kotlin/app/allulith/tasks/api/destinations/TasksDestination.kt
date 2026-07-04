package app.allulith.tasks.api.destinations

import androidx.navigation3.runtime.NavKey
import app.allulith.tasks.api.domain.Task
import kotlinx.serialization.Serializable

@Serializable
sealed class TasksDestination : NavKey {
    @Serializable
    data object TasksOverview : TasksDestination()
    @Serializable
    data class TaskCreation(val task: Task?) : TasksDestination()
}
