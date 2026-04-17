package app.allulith.signup.impl.destinations.accountCreation.data

import app.allulith.data.api.OrganiserDatabase
import app.allulith.data.api.entity.User
import app.allulith.signup.impl.destinations.accountCreation.domain.AccountCreationRepository
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

internal class AccountCreationRepositoryImpl @Inject constructor(
    private val database: OrganiserDatabase,
) : AccountCreationRepository {

    @OptIn(ExperimentalUuidApi::class)
    override suspend fun createUser(name: String) {
        val user = User(
            uid = Uuid.random().toString(),
            name = name,
        )

        database.userDao().insertAll(user)
    }
}
