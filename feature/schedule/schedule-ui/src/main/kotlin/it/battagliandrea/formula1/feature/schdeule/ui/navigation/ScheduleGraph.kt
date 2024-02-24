package it.battagliandrea.formula1.feature.schdeule.ui.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import it.battagliandrea.formula1.feature.schdeule.ui.ScheduleScreen
import it.battagliandrea.formula1.feature.schdeule.ui.ScheduleViewModel

const val SCHEDULE_ROUTE = "schedule"

fun NavController.navigateToSchedule() {
    navigate(SCHEDULE_ROUTE)
}

fun NavGraphBuilder.scheduleScreen() {
    composable(route = SCHEDULE_ROUTE) {
        val scheduleViewModel: ScheduleViewModel = hiltViewModel()
        val scheduleUiState by scheduleViewModel.state.collectAsStateWithLifecycle()

        ScheduleScreen(
            uiState = scheduleUiState,
        )
    }
}
