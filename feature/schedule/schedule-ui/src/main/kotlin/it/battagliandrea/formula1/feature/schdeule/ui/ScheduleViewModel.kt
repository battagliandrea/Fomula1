package it.battagliandrea.formula1.feature.schdeule.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import it.battagliandrea.formula1.core.ui.mvi.MVI
import it.battagliandrea.formula1.core.ui.mvi.mvi
import it.battagliandrea.formula1.feature.schdeule.ui.ScheduleContract.SideEffect
import it.battagliandrea.formula1.feature.schdeule.ui.ScheduleContract.UiAction
import it.battagliandrea.formula1.feature.schdeule.ui.ScheduleContract.UiState
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor() :
    ViewModel(), MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {

    override fun onAction(uiAction: UiAction) {}
}

private fun initialUiState() = UiState.Loading
