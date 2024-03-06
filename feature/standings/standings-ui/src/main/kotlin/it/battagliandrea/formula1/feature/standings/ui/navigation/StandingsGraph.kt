package it.battagliandrea.formula1.feature.standings.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import it.battagliandrea.formula1.feature.standings.ui.StandingsScreen

const val STANDINGS_ROUTE = "standings"

fun NavController.navigateToStangings() {
    navigate(STANDINGS_ROUTE)
}

fun NavGraphBuilder.standingsScreen() {
    composable(route = STANDINGS_ROUTE) {
        StandingsScreen()
    }
}
