package app.allulith.home.impl.destinations.home.ui

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import app.allulith.home.impl.destinations.home.domain.HomeRepository
import app.allulith.settings.api.destinations.SettingsDestination
import app.allulith.tasks.api.destinations.TasksDestination
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Stable
@HiltViewModel(assistedFactory = HomeViewModel.Factory::class)
internal class HomeViewModel @AssistedInject constructor(
    @Assisted private val backStack: NavBackStack<NavKey>,
    private val repository: HomeRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<Home.UiState>(Home.UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        personalizeHome()
    }

    private fun personalizeHome() {
        viewModelScope.launch {
            repository.getUserName().onRight { name ->
                _uiState.value = Home.UiState.Content(
                    name = name
                )
            }

            repository.getTasks().collectLatest { tasks ->
                _uiState.updateContentState {
                    it.copy(tasks = tasks)
                }
            }
        }
    }

    fun onUiEvent(uiEvent: Home.UiEvent) {
        when (uiEvent) {
            Home.UiEvent.OnSettingsTap -> navigateToSettings()
            is Home.UiEvent.OnTaskTap -> navigateToTasks(taskId = uiEvent.taskId)
            Home.UiEvent.OnAddTaskTap -> navigateToTasks(taskId = null)
        }
    }

    private fun navigateToSettings() {
        backStack.add(SettingsDestination.Settings)
    }

    private fun navigateToTasks(taskId: String?) {
        backStack.add(TasksDestination.TaskCreation(taskId = taskId))
    }

    fun MutableStateFlow<Home.UiState>.updateContentState(
        update: (Home.UiState.Content) -> Home.UiState.Content,
    ) {
        this.update { state ->
            when (state) {
                is Home.UiState.Content -> update(state)
                else -> state
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(backStack: NavBackStack<NavKey>): HomeViewModel
    }
}
