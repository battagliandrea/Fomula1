package it.battagliandrea.formula1.core.ui.compose

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp),
)

val topRoundedCornerShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp, 4.dp, 0.dp, 0.dp),
    small = RoundedCornerShape(8.dp, 48.dp, 0.dp, 0.dp),
    medium = RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp),
    large = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp),
    extraLarge = RoundedCornerShape(32.dp, 32.dp, 0.dp, 0.dp),
)
