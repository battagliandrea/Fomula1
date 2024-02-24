package it.battagliandrea.formula1.feature.main.navigation.navigation

import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import it.battagliandrea.formula1.core.ui.navigation.popUpToTop
import it.battagliandrea.formula1.feature.main.navigation.MainScreen

const val MAIN_ROUTE_GRAPH = "main-graph"
const val MAIN_ROUTE = "main"

fun NavController.navigateToMain() {
    navigate(MAIN_ROUTE)
}

fun NavController.navigateToMainGraph() {
    navigate(route = MAIN_ROUTE_GRAPH) {
        popUpToTop(this@navigateToMainGraph)
    }
}

fun NavGraphBuilder.mainGraph(
    builder: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = MAIN_ROUTE_GRAPH,
        startDestination = MAIN_ROUTE,
        builder = builder,
    )
}

fun NavGraphBuilder.mainScreen(
    startDestination: String,
    bottomBarBuilder: NavGraphBuilder.() -> Unit,
) {
    composable(route = MAIN_ROUTE) {
        MainScreen(
            startDestination = startDestination,
            bottomBarBuilder = bottomBarBuilder,
        )
    }
}
