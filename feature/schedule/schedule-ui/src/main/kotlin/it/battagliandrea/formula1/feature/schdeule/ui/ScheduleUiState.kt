package it.battagliandrea.formula1.feature.schdeule.ui

import it.battagliandrea.formula1.domain.models.Season

/**
 * Sealed interface representing the different UI states for displaying the race schedule.
 */
sealed interface ScheduleUiState {

    /**
     * Represents the UI state when schedule are being loaded.
     */
    data object Loading : ScheduleUiState

    /**
     * Represents the UI state when results are successfully loaded.
     *
     * @property seasons A list of [Season] objects representing the loaded seasons list.
     */
    data class Success(
        val seasons: List<Season>,
    ) : ScheduleUiState

    /**
     * Represents the UI state when an error occurs during the loading of race results.
     */
    data object Error : ScheduleUiState
}
