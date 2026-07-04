package app.allulith.routing.impl.destinations.routing.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

@Composable
internal fun RoutingRoute(
    backStack: NavBackStack<NavKey>,
    viewModel: RoutingViewModel = hiltViewModel(
        creationCallback = { factory: RoutingViewModel.Factory ->
            factory.create(backStack)
        }
    ),
) {
    viewModel.route()
}
