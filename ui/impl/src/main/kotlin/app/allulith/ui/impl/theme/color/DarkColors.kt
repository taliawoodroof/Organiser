package app.allulith.ui.impl.theme.color

import androidx.compose.material3.darkColorScheme

internal val darkColors = OrganiserColorScheme(
    primary = Colors.Dark.PRIMARY,
    onPrimary = Colors.Dark.ON_PRIMARY,
    background = Colors.Dark.BACKGROUND,
    onBackground = Colors.Dark.ON_BACKGROUND,
    error = Colors.Dark.ERROR,
    surface = Colors.Dark.SURFACE,
    onSurface = Colors.Dark.ON_SURFACE,
    surfaceVariant = Colors.Dark.SURFACE_VARIANT,
    onSurfaceVariant = Colors.Dark.ON_SURFACE_VARIANT,
)

internal val materialDarkColors = darkColorScheme(
    onSurface = Colors.Dark.Material.ON_SURFACE,
)
