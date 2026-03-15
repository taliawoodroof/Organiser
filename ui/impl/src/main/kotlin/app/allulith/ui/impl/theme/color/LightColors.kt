package app.allulith.ui.impl.theme.color

import androidx.compose.material3.lightColorScheme

internal val lightColors = OrganiserColorScheme(
    primary = Colors.Light.PRIMARY,
    onPrimary = Colors.Light.ON_PRIMARY,
    background = Colors.Light.BACKGROUND,
    onBackground = Colors.Light.ON_BACKGROUND,
    error = Colors.Light.ERROR,
    surface = Colors.Light.SURFACE,
    onSurface = Colors.Light.ON_SURFACE,
    surfaceVariant = Colors.Light.SURFACE_VARIANT,
    onSurfaceVariant = Colors.Light.ON_SURFACE_VARIANT,
)

internal val materialLightColors = lightColorScheme(
    onSurface = Colors.Light.Material.ON_SURFACE,
)
