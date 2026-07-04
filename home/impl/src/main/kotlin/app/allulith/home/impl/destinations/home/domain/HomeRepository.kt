package app.allulith.home.impl.destinations.home.domain

import app.allulith.tasks.api.domain.Task
import arrow.core.Either
import kotlinx.coroutines.flow.Flow

internal interface HomeRepository {
    suspend fun getUserName(): Either<NameNotFound, String>

    suspend fun getTasks(): Flow<List<Task>>
}
