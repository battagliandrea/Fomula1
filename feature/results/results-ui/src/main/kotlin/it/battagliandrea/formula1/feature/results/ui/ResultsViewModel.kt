package it.battagliandrea.formula1.feature.results.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.battagliandrea.formula1.core.ui.mvi.MVI
import it.battagliandrea.formula1.core.ui.mvi.mvi
import it.battagliandrea.formula1.domain.models.ErrorType
import it.battagliandrea.formula1.domain.models.Result
import it.battagliandrea.formula1.domain.usecase.GetCurrentLastResultUseCase
import it.battagliandrea.formula1.domain.usecase.GetCurrentLastResultUseCase.Params
import it.battagliandrea.formula1.feature.results.ui.ResultsContract.SideEffect
import it.battagliandrea.formula1.feature.results.ui.ResultsContract.UiAction
import it.battagliandrea.formula1.feature.results.ui.ResultsContract.UiAction.OnResultClick
import it.battagliandrea.formula1.feature.results.ui.ResultsContract.UiState
import it.battagliandrea.formula1.feature.results.ui.ResultsContract.UiState.Loading
import it.battagliandrea.formula1.feature.results.ui.ResultsContract.UiState.Success
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val getCurrentLastResultUseCase: GetCurrentLastResultUseCase,
) : ViewModel(), MVI<UiState, UiAction, SideEffect> by mvi(initialUiState()) {

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is OnResultClick -> handleOnResultClick()
        }
    }

    init {
        observeResults()
    }

    private fun observeResults() {
        viewModelScope.launch {
            getCurrentLastResultUseCase.execute(params = Params)
                .onStart { Loading }
                .collect { either ->
                    either.fold(
                        ifRight = ::toSuccessState,
                        ifLeft = ::toFailureState,
                    )
                }
        }
    }

    private fun toSuccessState(results: List<Result>) {
        updateUiState(Success(lastResults = results.take(3)))
    }

    private fun toFailureState(errorType: ErrorType) {
        updateUiState(UiState.Failure)
    }

    private fun handleOnResultClick() {
    }
}

private fun initialUiState() = Loading
