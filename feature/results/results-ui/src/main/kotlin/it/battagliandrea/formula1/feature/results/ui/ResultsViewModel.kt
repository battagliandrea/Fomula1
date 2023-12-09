package it.battagliandrea.formula1.feature.results.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.battagliandrea.formula1.core.resource.fold
import it.battagliandrea.formula1.domain.usecase.GetResultsUseCase
import it.battagliandrea.formula1.domain.usecase.GetResultsUseCase.Params
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val getResultsUseCase: GetResultsUseCase,
) : ViewModel() {

    val state = getResultsUseCase(params = Params(round = 1, year = 2023))
        .fold(
            isSuccess = { ResultsUiState.Success(races = it) },
            isError = { ResultsUiState.Error },
        )
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = ResultsUiState.Loading,
        )
}
