package it.battagliandrea.formula1.feature.schdeule.ui

class ScheduleContract {

    sealed class UiState {
        data object Success : UiState()
        data object Failure : UiState()
        data object Loading : UiState()
    }

    sealed interface UiAction

    sealed interface SideEffect
}
