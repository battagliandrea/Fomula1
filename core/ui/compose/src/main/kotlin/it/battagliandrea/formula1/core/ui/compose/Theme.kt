package it.battagliandrea.formula1.core.ui.compose

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun Formula1Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val configuration = LocalConfiguration.current
    val color = if (darkTheme) DarkColors else LightColors
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = color.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    ProvideDimens(dimensions = dimensions) {
        ProvideColors(color = color) {
            MaterialTheme(
                colorScheme = color,
                typography = Typography,
                content = content,
                shapes = shapes,
            )
        }
    }
}

object Formula1Theme {
    val colors: Dimensions
        @Composable
        get() = LocalAppDimens.current

    val dimens: Dimensions
        @Composable
        get() = LocalAppDimens.current
}

val Dimens: Dimensions
    @Composable
    get() = Formula1Theme.dimens
