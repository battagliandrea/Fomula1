package it.battagliandrea.formula1.feature.main.navigation

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import it.battagliandrea.formula1.core.ui.compose.Formula1Theme
import it.battagliandrea.formula1.core.ui.compose.Formula1Theme.colors
import it.battagliandrea.formula1.core.ui.compose.Formula1Theme.dimens
import it.battagliandrea.formula1.core.ui.resources.R
import it.battagliandrea.formula1.feature.main.navigation.navigation.NavigationItem.Home
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
        containerColor = colors.background,
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
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                startDestination = startDestination,
                innerPadding = innerPadding,
                bottomBarBuilder = bottomBarBuilder,
            )
        },
    )
}

@Composable
private fun MainTopBar(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .height(dimens.minimumToolbarHeight)
            .fillMaxWidth()
            .padding(horizontal = dimens.spacind_s, vertical = dimens.spacing_2xs),
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier.fillMaxHeight(),
            painter = painterResource(R.drawable.f1_logo),
            contentDescription = "",
            colorFilter = ColorFilter.tint(colors.brand),
            contentScale = ContentScale.Fit,
            alignment = Alignment.BottomStart,
        )
    }
}

@Composable
private fun MainBottomBar(
    navController: NavHostController,
    navigationBarCurrentItem: String,
    modifier: Modifier = Modifier,
) {
    val acceptedItems = listOf(Home, Schedule, Standings)

    Surface(
        shape = RoundedCornerShape(
            topStart = dimens.roundCorner_M,
            topEnd = dimens.roundCorner_M,
        ),
    ) {
        NavigationBar(
            modifier = modifier,
            containerColor = colors.primary,
        ) {
            acceptedItems.forEach { item ->
                NavigationBarItem(
                    selected = navigationBarCurrentItem == item.route,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = colors.primary,
                        selectedIconColor = colors.onPrimary,
                        selectedTextColor = colors.onPrimary,
                        unselectedIconColor = colors.onPrimaryUnselected,
                        unselectedTextColor = colors.onPrimaryUnselected,
                    ),
                    onClick = {
                        navController.navigate(item.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.iconRes),
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(text = item.label)
                    },
                )
            }
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
        verticalArrangement = Arrangement.spacedBy(dimens.spacind_s),
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            builder = bottomBarBuilder,
        )
    }
}

@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun MainScreenPreview() {
    Formula1Theme {
        MainScreen(
            startDestination = "home",
            modifier = Modifier.fillMaxSize(),
        ) {
            composable(route = "home") {
                Text(text = "Home Screen")
            }
        }
    }
}
