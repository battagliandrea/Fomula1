package it.battagliandrea.formula1.feature.standings.ui

class StandingsContract {

    sealed class UiState {
        data object Success : UiState()
        data object Failure : UiState()
        data object Loading : UiState()
    }

    sealed interface UiAction

    sealed interface SideEffect
}
