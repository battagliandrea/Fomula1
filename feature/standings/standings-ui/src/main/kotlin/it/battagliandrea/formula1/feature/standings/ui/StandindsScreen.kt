package it.battagliandrea.formula1.feature.standings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import it.battagliandrea.formula1.feature.standings.ui.StandingsContract.SideEffect
import it.battagliandrea.formula1.feature.standings.ui.StandingsContract.UiAction
import it.battagliandrea.formula1.feature.standings.ui.StandingsContract.UiState
import kotlinx.coroutines.flow.Flow

@Composable
fun StandingsScreen() {
    val viewModel: StandingsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    StandingsScreen(
        uiState = uiState,
        sideEffect = viewModel.sideEffect,
        onAction = viewModel::onAction,
    )
}

@Composable
fun StandingsScreen(
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
            color = MaterialTheme.colorScheme.background,
        ) {
            Text(text = "This is the standings screen!")
        }
    }
}
