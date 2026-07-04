package app.allulith.tasks.impl

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import app.allulith.tasks.api.destinations.TasksDestination
import app.allulith.tasks.impl.destinations.taskCreation.ui.TaskCreationRoute

internal fun EntryProviderScope<NavKey>.tasksNavigationBuilder(
    backStack: NavBackStack<NavKey>,
) {

    entry<TasksDestination.TaskCreation> {
        TaskCreationRoute(
            backStack = backStack,
            taskId = it.taskId,
        )
    }
}
