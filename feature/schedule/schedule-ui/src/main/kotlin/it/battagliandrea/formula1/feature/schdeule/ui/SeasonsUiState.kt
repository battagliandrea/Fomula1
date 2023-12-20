package it.battagliandrea.formula1.feature.schdeule.ui

import it.battagliandrea.formula1.domain.models.Season

/**
 * Sealed interface representing the different UI states for displaying the seasons list.
 */
sealed interface SeasonsUiState {

    /**
     * Represents the UI seasonsUiState when seasons are being loaded.
     */
    data object Loading : SeasonsUiState

    /**
     * Represents the UI seasonsUiState when seasons are successfully loaded.
     *
     * @property seasons A list of [Season] objects representing the loaded seasons list.
     */
    data class Success(
        val seasons: List<Season> = emptyList(),
    ) : SeasonsUiState
}
