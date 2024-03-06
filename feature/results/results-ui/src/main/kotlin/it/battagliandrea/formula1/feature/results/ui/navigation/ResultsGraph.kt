package it.battagliandrea.formula1.feature.results.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import it.battagliandrea.formula1.feature.results.ui.ResultsScreen

const val RESULTS_ROUTE = "results"

fun NavController.navigateToResults() {
    navigate(RESULTS_ROUTE)
}

fun NavGraphBuilder.resultsScreen() {
    composable(route = RESULTS_ROUTE) {
        ResultsScreen()
    }
}
