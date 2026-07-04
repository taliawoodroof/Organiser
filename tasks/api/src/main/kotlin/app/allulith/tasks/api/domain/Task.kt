package app.allulith.tasks.api.domain

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Serializable
@Immutable
data class Task(
    val id: String,
    val title: String,
    val description: String?,
    val hour: Int,
    val minute: Int,
)
