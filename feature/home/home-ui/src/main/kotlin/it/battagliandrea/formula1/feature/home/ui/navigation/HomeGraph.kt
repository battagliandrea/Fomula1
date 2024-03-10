package it.battagliandrea.formula1.feature.home.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import it.battagliandrea.formula1.feature.home.ui.HomeScreen

const val HOME_ROUTE = "home"

fun NavController.navigateToHome() {
    navigate(HOME_ROUTE)
}

fun NavGraphBuilder.homeScreen() {
    composable(route = HOME_ROUTE) {
        HomeScreen()
    }
}
