package it.battagliandrea.formula1.feature.standings.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import it.battagliandrea.formula1.core.ui.mvi.MVI
import it.battagliandrea.formula1.core.ui.mvi.mvi
import it.battagliandrea.formula1.feature.standings.ui.StandingsContract.SideEffect
import it.battagliandrea.formula1.feature.standings.ui.StandingsContract.UiAction
import it.battagliandrea.formula1.feature.standings.ui.StandingsContract.UiState
import javax.inject.Inject

@HiltViewModel
class StandingsViewModel @Inject constructor() :
    ViewModel(), MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {

    override fun onAction(uiAction: UiAction) {}
}

private fun initialUiState() = UiState.Loading
