package it.battagliandrea.formula1.feature.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import it.battagliandrea.formula1.domain.models.Result
import it.battagliandrea.formula1.feature.home.ui.HomeContract.SideEffect
import it.battagliandrea.formula1.feature.home.ui.HomeContract.UiAction
import it.battagliandrea.formula1.feature.home.ui.HomeContract.UiState
import it.battagliandrea.formula1.feature.home.ui.HomeContract.UiState.Success
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState,
        sideEffect = viewModel.sideEffect,
        onAction = viewModel::onAction,
    )
}

@Composable
fun HomeScreen(
    uiState: UiState,
    sideEffect: Flow<SideEffect>,
    onAction: (UiAction) -> Unit,
) {
    LaunchedEffect(sideEffect) {
        sideEffect.collect {
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            when (uiState) {
                is Success -> {
                    ResultWidget(results = uiState.lastResults)
                }
                else -> {
                    Text(text = "This is the Home screen!")
                }
            }
        }
    }
}

@Composable
private fun ResultWidget(
    modifier: Modifier = Modifier,
    results: List<Result>,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(items = results) { result ->
            Text(text = "${result.position} ${result.driver.code} ${result.time?.time.orEmpty()}")
        }
    }
}
