package app.allulith.signup.api.destinations

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class SignUpDestination : NavKey {
    @Serializable
    data object Welcome : SignUpDestination()
    @Serializable
    data object AccountCreation : SignUpDestination()
}
