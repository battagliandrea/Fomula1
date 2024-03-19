package it.battagliandrea.formula1.core.ui.compose

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Light
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold

val kanitFontFamily = FontFamily(
    Font(resId = R.font.kanit_light, weight = Light),
    Font(resId = R.font.kanit_regular, weight = Normal),
    Font(resId = R.font.kanit_italic, weight = Normal, style = Italic),
    Font(resId = R.font.kanit_medium, weight = Medium),
    Font(resId = R.font.kanit_semi_bold, weight = SemiBold),
    Font(resId = R.font.kanit_bold, weight = Bold),
)
