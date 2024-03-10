package formula1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import it.battagliandrea.formula1.feature.home.ui.navigation.HOME_ROUTE
import it.battagliandrea.formula1.feature.home.ui.navigation.homeScreen
import it.battagliandrea.formula1.feature.main.navigation.navigation.MAIN_ROUTE_GRAPH
import it.battagliandrea.formula1.feature.main.navigation.navigation.mainGraph
import it.battagliandrea.formula1.feature.main.navigation.navigation.mainScreen
import it.battagliandrea.formula1.feature.schdeule.ui.navigation.scheduleScreen
import it.battagliandrea.formula1.feature.standings.ui.navigation.standingsScreen

@Composable
fun RootHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MAIN_ROUTE_GRAPH,
    ) {
        mainGraph {
            mainScreen(
                startDestination = HOME_ROUTE,
                bottomBarBuilder = {
                    homeScreen()
                    standingsScreen()
                    scheduleScreen()
                },
            )
        }
    }
}
