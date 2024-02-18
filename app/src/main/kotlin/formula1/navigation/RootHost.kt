package formula1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import it.battagliandrea.formula1.core.ui.navigation.popBackStackOrFinish
import it.battagliandrea.formula1.feature.main.navigation.navigation.MAIN_ROUTE_GRAPH
import it.battagliandrea.formula1.feature.main.navigation.navigation.mainGraph
import it.battagliandrea.formula1.feature.main.navigation.navigation.mainScreen

@Composable
fun RootHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MAIN_ROUTE_GRAPH,
    ) {
        mainGraph {
            mainScreen(
                onNavigateBack = {
                    navController.popBackStackOrFinish()
                },
            )
        }
    }
}
