package it.battagliandrea.formula1.core.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimensions(
    val spacing_3xs: Dp,
    val spacing_2xs: Dp,
    val spacing_xs: Dp,
    val spacind_s: Dp,
    val spacing_m: Dp,
    val spacing_l: Dp,
    val icon_s: Dp,
    val icon_m: Dp,
    val icon_l: Dp,
    val roundCorner_S: Dp,
    val roundCorner_M: Dp,
    val roundCorner_L: Dp,
    val thickness_S: Dp,
    val thickness_M: Dp,
    val thickness_L: Dp,
    val minimumToolbarHeight: Dp = 52.dp,
)

val defaultDimensions = Dimensions(
    spacing_3xs = 2.dp,
    spacing_2xs = 4.dp,
    spacing_xs = 8.dp,
    spacind_s = 12.dp,
    spacing_m = 16.dp,
    spacing_l = 24.dp,
    icon_s = 24.dp,
    icon_m = 36.dp,
    icon_l = 72.dp,
    roundCorner_S = 8.dp,
    roundCorner_M = 16.dp,
    roundCorner_L = 24.dp,
    thickness_S = 1.dp,
    thickness_M = 2.dp,
    thickness_L = 4.dp,
)

@Composable
fun ProvideDimens(
    dimensions: Dimensions,
    content: @Composable () -> Unit,
) {
    val dimensionSet = remember { dimensions }
    CompositionLocalProvider(LocalAppDimens provides dimensionSet, content = content)
}

internal val LocalAppDimens = staticCompositionLocalOf {
    defaultDimensions
}
