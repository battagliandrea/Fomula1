package it.battagliandrea.formula1.core.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

interface Formula1ColorScheme {
    val brand: Color @Composable get
    val primary: Color @Composable get
    val onPrimary: Color @Composable get
    val onPrimaryUnselected: Color @Composable get
    val secondary: Color @Composable get
    val onSecondary: Color @Composable get
    val background: Color @Composable get
    val surface: Color @Composable get
    val onSurface: Color @Composable get
    val textPrimary: Color @Composable get
    val textSecondary: Color @Composable get
    val medalGold: Color @Composable get
    val medalSilver: Color @Composable get
    val medalBronze: Color @Composable get
    val medalText: Color @Composable get
    val dividerPrimary: Color @Composable get
    val dividerSecondary: Color @Composable get
    val avatarBackground: Color @Composable get
}

object Formula1ColorSchemes {
    object Light : Formula1ColorScheme {
        override val brand: Color @Composable get() = Color(0xFFBE0E00)
        override val primary: Color @Composable get() = Color(0xFFBE0E00)
        override val onPrimary: Color @Composable get() = Color(0xFFFFFFFF)
        override val onPrimaryUnselected: Color @Composable get() = Color(0xFFE0E0E0)
        override val secondary: Color @Composable get() = Color(0xFF400200)
        override val onSecondary: Color @Composable get() = Color(0xFFFFFFFF)
        override val background: Color @Composable get() = Color(0xFFFFFBFF)
        override val surface: Color @Composable get() = Color(0xFFFFFFFF)
        override val onSurface: Color @Composable get() = Color(0xFF1C1B1E)
        override val textPrimary: Color @Composable get() = Color(0xFF1C1B1E)
        override val textSecondary: Color @Composable get() = Color(0xFF49454E)
        override val medalGold: Color @Composable get() = Color(0xFFEAA105)
        override val medalSilver: Color @Composable get() = Color(0xFFC0C0C0)
        override val medalBronze: Color @Composable get() = Color(0xFF8B5B13)
        override val medalText: Color @Composable get() = Color(0xFFFFFFFF)
        override val dividerPrimary: Color @Composable get() = Color(0xFFBDBDBD)
        override val dividerSecondary: Color @Composable get() = Color(0xFFBE0E00)
        override val avatarBackground: Color @Composable get() = Color(0xFFBDBDBD)
    }

    object Dark : Formula1ColorScheme {
        override val brand: Color @Composable get() = Color(0xFFBE0E00)
        override val primary: Color @Composable get() = Color(0xFF1B1B27)
        override val onPrimary: Color @Composable get() = Color(0xFFFFFFFF)
        override val onPrimaryUnselected: Color @Composable get() = Color(0xFFE0E0E0)
        override val secondary: Color @Composable get() = Color(0xFF400200)
        override val onSecondary: Color @Composable get() = Color(0xFFFFFFFF)
        override val background: Color @Composable get() = Color(0xFF0B0B0B)
        override val surface: Color @Composable get() = Color(0xFF1B1B27)
        override val onSurface: Color @Composable get() = Color(0xFFEDE0DD)
        override val textPrimary: Color @Composable get() = Color(0xFFFFFFFF)
        override val textSecondary: Color @Composable get() = Color(0xFFFFFFFF)
        override val medalGold: Color @Composable get() = Color(0xFFEAA105)
        override val medalSilver: Color @Composable get() = Color(0xFFC0C0C0)
        override val medalBronze: Color @Composable get() = Color(0xFF8B5B13)
        override val medalText: Color @Composable get() = Color(0xFFFFFFFF)
        override val dividerPrimary: Color @Composable get() = Color(0xFF272736)
        override val dividerSecondary: Color @Composable get() = Color(0xFFBE0E00)
        override val avatarBackground: Color @Composable get() = Color(0xFF272736)
    }
}

data class Formula1ThemeColors(
    val brand: Color,
    val primary: Color,
    val onPrimary: Color,
    val onPrimaryUnselected: Color,
    val secondary: Color,
    val onSecondary: Color,
    val background: Color,
    val surface: Color,
    val onSurface: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val medalGold: Color,
    val medalSilver: Color,
    val medalBronze: Color,
    val medalText: Color,
    val dividerPrimary: Color,
    val dividerSecondary: Color,
    val avatarBackground: Color,
)

@Composable
fun buildThemeColors(colorScheme: Formula1ColorScheme) =
    Formula1ThemeColors(
        brand = colorScheme.brand,
        primary = colorScheme.primary,
        onPrimary = colorScheme.onPrimary,
        onPrimaryUnselected = colorScheme.onPrimaryUnselected,
        secondary = colorScheme.secondary,
        onSecondary = colorScheme.onSecondary,
        background = colorScheme.background,
        surface = colorScheme.surface,
        onSurface = colorScheme.onSurface,
        textPrimary = colorScheme.textPrimary,
        textSecondary = colorScheme.textSecondary,
        medalGold = colorScheme.medalGold,
        medalSilver = colorScheme.medalSilver,
        medalBronze = colorScheme.medalBronze,
        medalText = colorScheme.medalText,
        dividerPrimary = colorScheme.dividerPrimary,
        dividerSecondary = colorScheme.dividerSecondary,
        avatarBackground = colorScheme.avatarBackground,
    )

@Composable
fun ProvideColors(
    color: Formula1ThemeColors,
    content: @Composable () -> Unit,
) {
    val colorSet = remember { color }
    CompositionLocalProvider(LocalAppColors provides colorSet, content = content)
}

internal val LocalAppColors = staticCompositionLocalOf<Formula1ThemeColors> {
    error("No colors provided!")
}
