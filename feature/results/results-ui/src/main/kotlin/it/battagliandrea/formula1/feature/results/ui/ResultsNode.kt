package it.battagliandrea.formula1.feature.results.ui

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import it.battagliandrea.formula1.domain.models.Result

@Composable
fun ResultsNode(
    modifier: Modifier = Modifier,
    viewModel: ResultsViewModel = hiltViewModel(),
) {
    val resultsUiState by viewModel.resultsUiState.collectAsStateWithLifecycle()

    ResultScreen(
        modifier = modifier,
        resultsUiState = resultsUiState,
    )
}

@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    resultsUiState: ResultsUiState = ResultsUiState.Loading,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            when (resultsUiState) {
                ResultsUiState.Loading -> Unit
                is ResultsUiState.Success -> {
                    ResultsList(results = resultsUiState.races[0].results)
                }
                ResultsUiState.Error -> Unit
            }
        }
    }
}

@Composable
private fun ResultsList(
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

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    ResultScreen(
        resultsUiState = ResultsUiState.Success(
            races = emptyList(),
        ),
    )
}
