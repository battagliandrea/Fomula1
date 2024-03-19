package it.battagliandrea.formula1.core.ui.compose

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import it.battagliandrea.formula1.core.ui.compose.Formula1ColorSchemes.Dark
import it.battagliandrea.formula1.core.ui.compose.Formula1ColorSchemes.Light

@Composable
fun Formula1Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val color = if (darkTheme) buildThemeColors(colorScheme = Dark) else buildThemeColors(colorScheme = Light)
    val dimensions = defaultDimensions

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = color.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    ProvideDimens(dimensions = dimensions) {
        ProvideColors(color = color, content = content)
    }
}

object Formula1Theme {
    val colors: Formula1ThemeColors
        @Composable
        get() = LocalAppColors.current

    val dimens: Dimensions
        @Composable
        get() = LocalAppDimens.current
}
