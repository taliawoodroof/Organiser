package app.allulith.tasks.impl.destinations.taskCreation.ui

import android.content.Context
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import app.allulith.data.api.OrganiserDatabase
import app.allulith.data.api.entity.Task
import app.allulith.notification.api.domain.NotificationRepository
import app.allulith.notification.api.domain.Reminder
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Stable
@HiltViewModel(assistedFactory = TaskCreationViewModel.Factory::class)
internal class TaskCreationViewModel @AssistedInject constructor(
    @param:ApplicationContext val context: Context,
    @Assisted private val backStack: NavBackStack<NavKey>,
    @Assisted private val taskId: String?,
    private val database: OrganiserDatabase,
    private val notificationRepository: NotificationRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<TaskCreation.UiState> = MutableStateFlow(
        TaskCreation.UiState.Loading,
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = if (taskId != null) {
                val task = database.taskDao().get(taskId = taskId)
                if (task != null) {
                    TaskCreation.UiState.Content(
                        taskTitle = task.title,
                        taskDescription = task.description ?: "",
                        hour = task.hour,
                        minute = task.minute,
                        taskState = TaskCreation.TaskState.Edit,
                    )
                } else {
                    TaskCreation.UiState.Content(
                        taskState = TaskCreation.TaskState.New,
                    )
                }
            } else {
                TaskCreation.UiState.Content(
                    taskState = TaskCreation.TaskState.New,
                )
            }
        }
    }

    fun onUiEvent(uiEvent: TaskCreation.UiEvent) {
        when (uiEvent) {
            TaskCreation.UiEvent.OnBackTap -> goBack()
            TaskCreation.UiEvent.OnCreateTaskTap -> createOrUpdateTask(updateTask = false)
            is TaskCreation.UiEvent.OnDescriptionChange -> onDescriptionChange(text = uiEvent.text)
            is TaskCreation.UiEvent.OnTitleChange -> onTitleChange(text = uiEvent.text)
            TaskCreation.UiEvent.OnShowTimerPicker -> showTimePicker()
            TaskCreation.UiEvent.OnDismissTimePickerDialog -> hideTimePicker()
            is TaskCreation.UiEvent.OnTimeChange ->
                onTimeChange(hour = uiEvent.hour, minute = uiEvent.minute)
            TaskCreation.UiEvent.OnDeleteTap -> deleteTask()
            TaskCreation.UiEvent.OnUpdateTaskTap -> createOrUpdateTask(updateTask = true)
        }
    }

    private fun goBack() {
        backStack.removeLastOrNull()
    }

    private fun onDescriptionChange(text: String) {
        _uiState.updateContentState {
            it.copy(taskDescription = text)
        }
    }

    private fun onTitleChange(text: String) {
        _uiState.updateContentState {
            it.copy(
                taskTitle = text,
                taskTitleError = false,
            )
        }
    }

    private fun showTimePicker() {
        _uiState.updateContentState {
            it.copy(
                isTimePickerVisible = true,
            )
        }
    }

    private fun hideTimePicker() {
        _uiState.updateContentState {
            it.copy(
                isTimePickerVisible = false,
            )
        }
    }

    private fun onTimeChange(
        hour: Int,
        minute: Int,
    ) {
        _uiState.updateContentState {
            it.copy(
                hour = hour,
                minute = minute,
                isTimePickerVisible = false,
                timeError = false,
            )
        }
    }

    private fun deleteTask() {
        viewModelScope.launch {
            if (taskId != null) {
                database.taskDao().delete(uid = taskId)
                goBack()
            }
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun createOrUpdateTask(
        updateTask: Boolean,
    ) {
        val uiState = _uiState.value as? TaskCreation.UiState.Content

        if (uiState == null || (taskId == null && updateTask)) {
            return
        }

        if (uiState.taskTitle.isBlank() || uiState.hour == null || uiState.minute == null) {
            _uiState.updateContentState {
                it.copy(
                    taskTitleError = uiState.taskTitle.isBlank(),
                    timeError = uiState.hour == null || uiState.minute == null,
                )
            }
        } else {
            viewModelScope.launch {
                val task = Task(
                    uid = taskId ?: Uuid.random().toString(),
                    title = uiState.taskTitle,
                    description = uiState.taskDescription.ifEmpty { null },
                    hour = uiState.hour,
                    minute = uiState.minute,
                )

                if (updateTask) {
                    database.taskDao().update(task)
                    notificationRepository.cancelReminder(
                        context = context,
                        reminderId = task.convertUidToInt(),
                    )
                } else {
                    database.taskDao().insertAll(task)
                }

                setReminder(task = task)
                goBack()
            }
        }
    }

    private fun setReminder(task: Task) {
        notificationRepository.scheduleExactReminder(
            context = context,
            reminder = Reminder(
                id = task.convertUidToInt(),
                hour = task.hour,
                minute = task.minute,
                title = task.title,
                message = task.description,
            )
        )
    }

    fun MutableStateFlow<TaskCreation.UiState>.updateContentState(
        update: (TaskCreation.UiState.Content) -> TaskCreation.UiState.Content,
    ) {
        this.update { state ->
            when (state) {
                is TaskCreation.UiState.Content -> update(state)
                else -> state
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            backStack: NavBackStack<NavKey>,
            taskId: String?,
        ): TaskCreationViewModel
    }
}
