package it.battagliandrea.formula1.feature.results.ui

import it.battagliandrea.formula1.domain.models.Race

sealed interface ResultsUiState {

    data object Loading : ResultsUiState

    data class Success(
        val races: List<Race>,
    ) : ResultsUiState

    data object Error : ResultsUiState
}
