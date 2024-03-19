package it.battagliandrea.formula1.core.ui.compose.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularIndicatorWidget(
    modifier: Modifier = Modifier,
    width: Dp = 52.dp,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .width(width),
            color = color,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CircularIndicatorWidgetPreview() {
    CircularIndicatorWidget(
        modifier = Modifier.fillMaxSize(),
        color = Color.Red,
    )
}
