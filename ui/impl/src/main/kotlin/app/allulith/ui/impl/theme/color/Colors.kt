@file:Suppress("MagicNumber")

package app.allulith.ui.impl.theme.color

import androidx.compose.ui.graphics.Color

internal object Colors {
    object Light {
        val PRIMARY = Color(0xFF88F1B0)
        val ON_PRIMARY = Color(0xFF040308)
        val BACKGROUND = Color(0xFFF2EFE9)
        val ON_BACKGROUND = Color(0xFF040308)
        val ERROR = Color(0xFFE5183E)
        val SURFACE = Color(0xFFE1DDD8)
        val ON_SURFACE = Color(0xFF040308)
        val SURFACE_VARIANT = Color(0xFFD5CFC9)
        val ON_SURFACE_VARIANT = Color(0xFF040308)

        object Material {
            val ON_SURFACE = Color(0xFF040308)
        }
    }

    object Dark {
        val PRIMARY = Color(0xFF79C99E)
        val ON_PRIMARY = Color(0xFF040308)
        val BACKGROUND = Color(0xFF040308)
        val ON_BACKGROUND = Color(0xFFF2EFE9)
        val ERROR = Color(0xFFFF1568)
        val SURFACE = Color(0xFF1D1D28)
        val ON_SURFACE = Color(0xFFF2EFE9)
        val SURFACE_VARIANT = Color(0xFF303041)
        val ON_SURFACE_VARIANT = Color(0xFFF2EFE9)

        object Material {
            val ON_SURFACE = Color(0xFFF2EFE9)
        }
    }
}
