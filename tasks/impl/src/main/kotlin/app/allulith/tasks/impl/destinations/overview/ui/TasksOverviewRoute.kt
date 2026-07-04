package app.allulith.tasks.impl.destinations.overview.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import app.allulith.tasks.api.domain.Task
import app.allulith.tasks.impl.R
import app.allulith.ui.impl.components.appbars.OrganiserTopBar
import app.allulith.ui.impl.components.cards.OrganiserRowCard
import app.allulith.ui.impl.components.fab.OrganiserFloatingActionButton
import app.allulith.ui.impl.templates.OrganiserScreen
import app.allulith.ui.impl.text.OrganiserBodyText
import app.allulith.ui.impl.text.OrganiserSubHeaderText
import app.allulith.ui.impl.theme.OrganiserTheme

@Composable
internal fun TasksOverviewRoute(
    backStack: NavBackStack<NavKey>,
    viewModel: TasksOverviewViewModel = hiltViewModel(
        key = backStack.hashCode().toString(),
        creationCallback = { factory: TasksOverviewViewModel.Factory ->
            factory.create(backStack = backStack)
        }
    ),
) {
    OverviewScreen(
        uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
        onUiEvent = viewModel::onUiEvent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OverviewScreen(
    uiState: TasksOverview.UiState,
    onUiEvent: (TasksOverview.UiEvent) -> Unit,
) {
    OrganiserScreen(
        header = stringResource(R.string.tasks_overview_header),
        description = stringResource(R.string.tasks_overview_description),
        topBarContent = {
            OrganiserTopBar(
                onBack = {
                    onUiEvent(TasksOverview.UiEvent.OnBack)
                },
            )
        },
        floatingActionButtonContent = {
            OrganiserFloatingActionButton(
                onClick = {
                    onUiEvent(TasksOverview.UiEvent.OnAddTask)
                },
                toolTip = stringResource(R.string.tasks_overview_add_task_tooltip),
                icon = R.drawable.ic_add,
                iconDescription = stringResource(R.string.tasks_overview_add_task_content_description),
            )
        },
    ) {
        when (uiState.tasks) {
            TasksOverview.TasksStructure.NoTasks -> {
                NoTasks()
            }
            is TasksOverview.TasksStructure.Tasks -> {
                Tasks(
                    tasks = uiState.tasks,
                    onUiEvent = onUiEvent,
                )
            }
        }
    }
}

@Composable
private fun NoTasks() {
    Column(
        verticalArrangement = Arrangement.spacedBy(OrganiserTheme.dimensions.dim050),
    ) {
        OrganiserSubHeaderText(
            text = stringResource(R.string.tasks_overview_subheader_no_tasks),
        )
        OrganiserBodyText(
            text = stringResource(R.string.tasks_overview_description_no_tasks),
        )
    }
}

@Composable
private fun Tasks(
    tasks: TasksOverview.TasksStructure.Tasks,
    onUiEvent: (TasksOverview.UiEvent) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(OrganiserTheme.dimensions.dim100),
    ) {
        OrganiserSubHeaderText(
            text = stringResource(R.string.tasks_overview_subheader_tasks),
        )
        tasks.tasks.forEach { task ->
            TaskCard(
                task = task,
                onTaskClick = {
                    onUiEvent(TasksOverview.UiEvent.OnViewTask(task = task))
                },
            )
        }
    }
}

@Composable
private fun TaskCard(
    task: Task,
    onTaskClick: () -> Unit,
) {
    OrganiserRowCard(
        onClick = {
            onTaskClick()
        },
        header = task.title,
        description = task.description,
    )
}

@PreviewLightDark
@Composable
private fun OverviewScreenNoTasksPreview() {
    OrganiserTheme {
        OverviewScreen(
            uiState = TasksOverview.UiState(),
            onUiEvent = {},
        )
    }
}

@PreviewLightDark
@Composable
private fun OverviewScreenTasksPreview() {
    OrganiserTheme {
        OverviewScreen(
            uiState = TasksOverview.UiState(
                tasks = TasksOverview.TasksStructure.Tasks(
                    listOf(
                        Task(
                            id = "1",
                            title = "Take medication",
                            description = "Take 2 pills",
                            hour = 17,
                            minute = 30,
                        ),
                        Task(
                            id = "2",
                            title = "Walk doggo",
                            description = null,
                            hour = 17,
                            minute = 30,
                        ),
                    )
                ),
            ),
            onUiEvent = {},
        )
    }
}
