package it.battagliandrea.formula1.feature.schdeule.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.battagliandrea.formula1.domain.usecase.GetSeasonsUseCase
import it.battagliandrea.formula1.domain.usecase.GetSeasonsUseCase.Params
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val getSeasonsUseCase: GetSeasonsUseCase,
) : ViewModel() {

    val state = getSeasonsUseCase.execute(Params)
        .map { either ->
            either.fold(
                ifRight = { ScheduleUiState.Success(seasons = it) },
                ifLeft = { ScheduleUiState.Error },
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = ScheduleUiState.Loading,
        )
}
