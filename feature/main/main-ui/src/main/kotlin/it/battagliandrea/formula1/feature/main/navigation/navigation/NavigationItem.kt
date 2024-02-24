package it.battagliandrea.formula1.feature.main.navigation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

internal sealed class NavigationItem(val route: String, val icon: ImageVector, val label: String) {
    data object Results : NavigationItem("results", Icons.Default.List, "Results")
    data object Schedule : NavigationItem("schedule", Icons.Default.List, "Schedule")
    data object Standings : NavigationItem("standings", Icons.Default.List, "Standings")
}
