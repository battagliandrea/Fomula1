package it.battagliandrea.formula1.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.battagliandrea.formula1.core.ui.mvi.MVI
import it.battagliandrea.formula1.core.ui.mvi.mvi
import it.battagliandrea.formula1.domain.models.ErrorType
import it.battagliandrea.formula1.domain.models.Result
import it.battagliandrea.formula1.domain.models.toPodium
import it.battagliandrea.formula1.domain.usecase.GetCurrentLastResultUseCase
import it.battagliandrea.formula1.feature.home.ui.HomeContract.NextRaceUiState
import it.battagliandrea.formula1.feature.home.ui.HomeContract.RecentResultUiState
import it.battagliandrea.formula1.feature.home.ui.HomeContract.SideEffect
import it.battagliandrea.formula1.feature.home.ui.HomeContract.UiAction
import it.battagliandrea.formula1.feature.home.ui.HomeContract.UiAction.OnResultClick
import it.battagliandrea.formula1.feature.home.ui.HomeContract.UiState
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentLastResultUseCase: GetCurrentLastResultUseCase,
) : ViewModel(), MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is OnResultClick -> handleOnResultClick()
        }
    }

    init {
        observeLastResults()
    }

    private fun observeLastResults() {
        viewModelScope.launch {
            getCurrentLastResultUseCase.execute(params = GetCurrentLastResultUseCase.Params)
                .onStart { RecentResultUiState.Loading }
                .collect { either ->
                    either.fold(
                        ifRight = ::toSuccessState,
                        ifLeft = ::toFailureState,
                    )
                }
        }
    }

    private fun toSuccessState(results: List<Result>) {
        updateUiState {
            copy(
                recentResultUiState = RecentResultUiState.Success(
                    podium = results.toPodium(),
                ),
            )
        }
    }

    private fun toFailureState(errorType: ErrorType) {
        updateUiState {
            copy(recentResultUiState = RecentResultUiState.Failure)
        }
    }

    private fun handleOnResultClick() {
    }
}

private fun initialUiState() =
    UiState(
        nextRaceUiState = NextRaceUiState.Loading,
        recentResultUiState = RecentResultUiState.Loading,
    )
