package app.allulith.home.impl.destinations.home.ui

import app.allulith.tasks.api.domain.Task

internal object Home {

    sealed class UiState {
        data object Loading : UiState()
        data class Content(
            val name: String = "",
            val tasks: List<Task> = emptyList(),
        ) : UiState()
    }

    sealed class UiEvent {
        data object OnSettingsTap : UiEvent()
        data class OnTaskTap(val taskId: String) : UiEvent()
        data object OnAddTaskTap : UiEvent()
    }
}
