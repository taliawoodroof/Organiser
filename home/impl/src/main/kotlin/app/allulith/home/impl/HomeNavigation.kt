package app.allulith.home.impl

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import app.allulith.home.api.destinations.HomeDestination
import app.allulith.home.impl.destinations.home.ui.HomeRoute

internal fun EntryProviderScope<NavKey>.homeNavigationBuilder(
    backStack: NavBackStack<NavKey>,
) {
    entry<HomeDestination.Home> {
        HomeRoute(backStack = backStack)
    }
}
