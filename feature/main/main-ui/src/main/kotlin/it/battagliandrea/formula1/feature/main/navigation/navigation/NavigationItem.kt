package it.battagliandrea.formula1.feature.main.navigation.navigation

import it.battagliandrea.formula1.core.ui.resources.R

internal sealed class NavigationItem(val route: String, val iconRes: Int, val label: String) {

    data object Results : NavigationItem(
        route = "results",
        iconRes = R.drawable.ic_results,
        label = "Results",
    )
    data object Schedule : NavigationItem(
        route = "schedule",
        iconRes = R.drawable.ic_schedule,
        label = "Schedule",
    )
    data object Standings : NavigationItem(
        route = "standings",
        iconRes = R.drawable.ic_standings,
        label = "Standings",
    )
}
