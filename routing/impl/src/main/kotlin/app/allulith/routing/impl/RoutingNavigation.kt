package app.allulith.routing.impl

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import app.allulith.routing.api.destinations.RoutingDestination
import app.allulith.routing.impl.destinations.routing.ui.RoutingRoute

internal fun EntryProviderScope<NavKey>.routingNavigationBuilder(
    backStack: NavBackStack<NavKey>,
) {
    entry<RoutingDestination.Routing> {
        RoutingRoute(backStack = backStack)
    }
}
