package app.allulith.home.impl.destinations.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import app.allulith.home.impl.R
import app.allulith.ui.impl.components.appbars.OrganiserTopBar
import app.allulith.ui.impl.components.appbars.OrganiserTopBarAction
import app.allulith.ui.impl.components.cards.OrganiserRowCard
import app.allulith.ui.impl.components.fab.OrganiserFloatingActionButton
import app.allulith.ui.impl.components.loading.OrganiserLoader
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
    when (uiState) {
        is Home.UiState.Content -> Content(
            uiState = uiState,
            onUiEvent = onUiEvent,
        )
        Home.UiState.Loading -> OrganiserLoader()
    }
}

@Composable
private fun Content(
    uiState: Home.UiState.Content,
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
        floatingActionButtonContent = {
            OrganiserFloatingActionButton(
                onClick = {
                    onUiEvent(Home.UiEvent.OnAddTaskTap)
                },
                toolTip = stringResource(R.string.home_tasks_add_task_tooltip),
                icon = R.drawable.ic_add,
                iconDescription = stringResource(R.string.cd_add_task),
            )
        },
    ) {
        uiState.tasks.forEach { task ->
            OrganiserRowCard(
                onClick = {
                    onUiEvent(Home.UiEvent.OnTaskTap(task.id))
                },
                header = task.title,
                description = task.description,
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun HomeScreenPreview() {
    OrganiserTheme {
        HomeScreen(
            uiState = Home.UiState.Content(
                name = "Nota Areal",
                tasks = emptyList(),
            ),
            onUiEvent = {},
        )
    }
}
