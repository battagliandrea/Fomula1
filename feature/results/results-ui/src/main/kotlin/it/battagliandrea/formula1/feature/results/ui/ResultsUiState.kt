package it.battagliandrea.formula1.feature.results.ui

import it.battagliandrea.formula1.domain.models.Race

/**
 * Sealed interface representing the different UI states for displaying race results.
 */
sealed interface ResultsUiState {

    /**
     * Represents the UI state when results are being loaded.
     */
    data object Loading : ResultsUiState

    /**
     * Represents the UI state when results are successfully loaded.
     *
     * @property races A list of [Race] objects representing the loaded race results.
     */
    data class Success(
        val races: List<Race>,
    ) : ResultsUiState

    /**
     * Represents the UI state when an error occurs during the loading of race results.
     */
    data object Error : ResultsUiState
}
