package app.allulith.settings.impl

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import app.allulith.settings.api.destinations.SettingsDestination
import app.allulith.settings.impl.destinations.settings.ui.SettingsRoute

internal fun EntryProviderScope<NavKey>.settingsNavigationBuilder(
    backStack: NavBackStack<NavKey>,
) {
    entry<SettingsDestination.Settings> {
        SettingsRoute(backStack = backStack)
    }
}
