package it.battagliandrea.formula1.feature.schdeule.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import it.battagliandrea.formula1.core.ui.compose.Formula1Theme.colors
import it.battagliandrea.formula1.feature.schdeule.ui.ScheduleContract.SideEffect
import it.battagliandrea.formula1.feature.schdeule.ui.ScheduleContract.UiAction
import it.battagliandrea.formula1.feature.schdeule.ui.ScheduleContract.UiState
import kotlinx.coroutines.flow.Flow

@Composable
fun ScheduleScreen() {
    val viewModel: ScheduleViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ScheduleScreen(
        uiState = uiState,
        sideEffect = viewModel.sideEffect,
        onAction = viewModel::onAction,
    )
}

@Composable
fun ScheduleScreen(
    uiState: UiState,
    sideEffect: Flow<SideEffect>,
    onAction: (UiAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            color = colors.background,
        ) {
            Text(text = "This is the schedule screen!")
        }
    }
}
