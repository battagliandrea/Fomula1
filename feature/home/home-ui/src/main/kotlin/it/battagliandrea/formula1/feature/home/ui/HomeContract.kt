package it.battagliandrea.formula1.feature.home.ui

import it.battagliandrea.formula1.domain.models.Podium

class HomeContract {

    data class UiState(
        val nextRaceUiState: NextRaceUiState,
        val recentResultUiState: RecentResultUiState,
    )

    sealed class NextRaceUiState {
        data object Success : NextRaceUiState()
        data object Failure : NextRaceUiState()
        data object Loading : NextRaceUiState()
    }

    sealed class RecentResultUiState {
        data class Success(val podium: Podium) : RecentResultUiState()
        data object Failure : RecentResultUiState()
        data object Loading : RecentResultUiState()
    }

    sealed interface UiAction {
        data object OnResultClick : UiAction
    }

    sealed interface SideEffect
}
