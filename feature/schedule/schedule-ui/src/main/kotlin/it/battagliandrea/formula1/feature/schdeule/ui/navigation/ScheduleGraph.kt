package it.battagliandrea.formula1.feature.schdeule.ui.navigation

import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import it.battagliandrea.formula1.feature.schdeule.ui.ScheduleScreen

const val SCHEDULE_ROUTE = "schedule"

fun NavController.navigateToSchedule() {
    navigate(SCHEDULE_ROUTE)
}

fun NavGraphBuilder.scheduleScreen() {
    composable(route = SCHEDULE_ROUTE) {
        ScheduleScreen()
    }
}
