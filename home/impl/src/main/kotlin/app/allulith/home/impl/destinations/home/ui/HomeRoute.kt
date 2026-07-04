package app.allulith.home.impl.destinations.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import app.allulith.home.impl.R
import app.allulith.ui.impl.components.appbars.OrganiserTopBar
import app.allulith.ui.impl.components.appbars.OrganiserTopBarAction
import app.allulith.ui.impl.components.cards.OrganiserRowCard
import app.allulith.ui.impl.templates.OrganiserScreen
import app.allulith.ui.impl.theme.OrganiserTheme

@Composable
internal fun HomeRoute(
    backStack: NavBackStack<NavKey>,
    viewModel: HomeViewModel = hiltViewModel(
        key = backStack.hashCode().toString(),
        creationCallback = { factory: HomeViewModel.Factory ->
            factory.create(backStack)
        }
    ),
) {
    HomeScreen(
        uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
        onUiEvent = viewModel::onUiEvent,
    )
}

@Composable
private fun HomeScreen(
    uiState: Home.UiState,
    onUiEvent: (Home.UiEvent) -> Unit,
) {
    OrganiserScreen(
        header = stringResource(R.string.home_header, uiState.name),
        description = stringResource(R.string.home_description),
        topBarContent = {
            OrganiserTopBar(
                actions = listOf(
                    OrganiserTopBarAction(
                        contentDescription = stringResource(R.string.cd_home_settings),
                        image = R.drawable.ic_settings,
                        onClick = {
                            onUiEvent(Home.UiEvent.OnSettingsTap)
                        },
                    )
                ),
            )
        },
    ) {
        TasksRow(onUiEvent = onUiEvent)
        GoalsRow(onUiEvent = onUiEvent)
        RemindersRow(onUiEvent = onUiEvent)
    }
}

@Composable
private fun TasksRow(
    onUiEvent: (Home.UiEvent) -> Unit,
) {
    Column {
        Spacer(modifier = Modifier.height(OrganiserTheme.dimensions.dim150))
        OrganiserRowCard(
            leadingIcon = R.drawable.ic_task,
            onClick = {
                onUiEvent(Home.UiEvent.OnTasksTap)
            },
            header = stringResource(R.string.home_tasks_row_header),
        )
    }
}

@Composable
private fun GoalsRow(
    onUiEvent: (Home.UiEvent) -> Unit,
) {
    OrganiserRowCard(
        leadingIcon = R.drawable.ic_goal,
        onClick = {
            onUiEvent(Home.UiEvent.OnGoalsTap)
        },
        header = stringResource(R.string.home_goals_row_header),
    )
}

@Composable
private fun RemindersRow(
    onUiEvent: (Home.UiEvent) -> Unit,
) {
    OrganiserRowCard(
        leadingIcon = R.drawable.ic_reminder,
        onClick = {
            onUiEvent(Home.UiEvent.OnRemindersTap)
        },
        header = stringResource(R.string.home_reminders_row_header),
    )
}

@PreviewLightDark
@Composable
private fun HomeScreenPreview() {
    OrganiserTheme {
        HomeScreen(
            uiState = Home.UiState(
                name = "Nota Areal",
            ),
            onUiEvent = {},
        )
    }
}
