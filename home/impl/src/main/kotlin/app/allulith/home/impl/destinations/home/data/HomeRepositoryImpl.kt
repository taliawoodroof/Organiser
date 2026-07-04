package app.allulith.home.impl.destinations.home.data

import app.allulith.data.api.OrganiserDatabase
import app.allulith.home.impl.destinations.home.domain.HomeRepository
import app.allulith.home.impl.destinations.home.domain.NameNotFound
import app.allulith.tasks.api.domain.Task
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class HomeRepositoryImpl @Inject constructor(
    private val database: OrganiserDatabase,
) : HomeRepository {

    override suspend fun getUserName(): Either<NameNotFound, String> {
        val name = database.userDao().getAll().firstOrNull()?.name
        return name?.right() ?: NameNotFound.left()
    }

    override suspend fun getTasks(): Flow<List<Task>> {
        return database.taskDao().getAll().map { tasks ->
            tasks.map {
                Task(
                    id = it.uid,
                    title = it.title,
                    description = it.description,
                    hour = it.hour,
                    minute = it.minute,
                )
            }
        }
    }
}
