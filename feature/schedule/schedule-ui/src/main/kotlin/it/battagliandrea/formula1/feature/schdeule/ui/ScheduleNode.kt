package it.battagliandrea.formula1.feature.schdeule.ui

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
import it.battagliandrea.formula1.domain.models.Season

@Composable
fun ScheduleNode(
    modifier: Modifier = Modifier,
    viewModel: ScheduleViewModel = hiltViewModel(),
) {
    val seasonsUiState by viewModel.seasonsUiState.collectAsStateWithLifecycle()

    ScheduleScreen(
        modifier = modifier.fillMaxSize(),
        seasonsUiState = seasonsUiState,
    )
}

@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    seasonsUiState: SeasonsUiState = SeasonsUiState.Loading,
) {
    Column(
        modifier = modifier,
    ) {
        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            when (seasonsUiState) {
                SeasonsUiState.Loading -> Unit
                is SeasonsUiState.Success -> {
                    SeasonsList(results = seasonsUiState.seasons)
                }
            }
        }
    }
}

@Composable
private fun SeasonsList(
    modifier: Modifier = Modifier,
    results: List<Season>,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(items = results) { result ->
            Text(text = "${result.year}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    ScheduleScreen(
        seasonsUiState = SeasonsUiState.Success(
            seasons = emptyList(),
        ),
    )
}
