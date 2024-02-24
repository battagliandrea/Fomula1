package it.battagliandrea.formula1.feature.main.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import it.battagliandrea.formula1.feature.main.navigation.navigation.NavigationItem.Results
import it.battagliandrea.formula1.feature.main.navigation.navigation.NavigationItem.Schedule
import it.battagliandrea.formula1.feature.main.navigation.navigation.NavigationItem.Standings

@Composable
internal fun MainScreen(
    startDestination: String,
    modifier: Modifier = Modifier,
    bottomBarBuilder: NavGraphBuilder.() -> Unit,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopBar()
        },
        bottomBar = {
            MainBottomBar(
                navController = navController,
                navigationBarCurrentItem = currentDestination?.route.orEmpty(),
            )
        },
        content = { innerPadding ->
            MainContent(
                navController = navController,
                startDestination = startDestination,
                innerPadding = innerPadding,
                bottomBarBuilder = bottomBarBuilder,
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainTopBar(
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        colors = topAppBarColors(
            containerColor = colorScheme.primaryContainer,
            titleContentColor = colorScheme.primary,
        ),
        title = {
            Text("Formula 1")
        },
    )
}

@Composable
private fun MainBottomBar(
    navController: NavHostController,
    navigationBarCurrentItem: String,
    modifier: Modifier = Modifier,
) {
    val accepterItems = listOf(
        Results,
        Schedule,
        Standings,
    )
    NavigationBar(
        modifier = modifier,
    ) {
        accepterItems.forEach { item ->
            NavigationBarItem(
                selected = navigationBarCurrentItem == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.label) },
            )
        }
    }
}

@Composable
private fun MainContent(
    navController: NavHostController,
    startDestination: String,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
    bottomBarBuilder: NavGraphBuilder.() -> Unit,
) {
    Column(
        modifier = modifier
            .padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            builder = bottomBarBuilder,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        startDestination = "results",
        modifier = Modifier.fillMaxSize(),
    ) {
        composable(route = "results") {
            Text(text = "Result Screen")
        }
    }
}
