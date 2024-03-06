package it.battagliandrea.formula1.feature.results.ui

import it.battagliandrea.formula1.domain.models.Result

class ResultsContract {

    sealed class UiState {
        data class Success(val lastResults: List<Result>) : UiState()
        data object Failure : UiState()
        data object Loading : UiState()
    }

    sealed interface UiAction {
        data object OnResultClick : UiAction
    }

    sealed interface SideEffect
}
