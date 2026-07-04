package app.allulith.routing.impl.destinations.routing.ui

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

@Composable
internal fun RoutingRoute(
    backStack: NavBackStack<NavKey>,
    viewModel: RoutingViewModel = hiltViewModel(
        key = backStack.hashCode().toString(),
        creationCallback = { factory: RoutingViewModel.Factory ->
            factory.create(backStack)
        }
    ),
) {
    viewModel.route()
}
