package it.battagliandrea.formula1.feature.home.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import it.battagliandrea.formula1.core.ui.compose.Formula1Theme
import it.battagliandrea.formula1.core.ui.compose.Formula1Theme.colors
import it.battagliandrea.formula1.core.ui.compose.Formula1Theme.dimens
import it.battagliandrea.formula1.core.ui.compose.components.CircularIndicatorWidget
import it.battagliandrea.formula1.core.ui.compose.kanitFontFamily
import it.battagliandrea.formula1.core.ui.resources.ResourceProvider
import it.battagliandrea.formula1.domain.models.Result
import it.battagliandrea.formula1.domain.models.completeName
import it.battagliandrea.formula1.domain.models.mock.ResultsMock.mockPodium
import it.battagliandrea.formula1.feature.home.ui.HomeContract.NextRaceUiState
import it.battagliandrea.formula1.feature.home.ui.HomeContract.RecentResultUiState
import it.battagliandrea.formula1.feature.home.ui.HomeContract.SideEffect
import it.battagliandrea.formula1.feature.home.ui.HomeContract.UiAction
import it.battagliandrea.formula1.feature.home.ui.HomeContract.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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
            .fillMaxSize()
            .background(color = colors.background),
    ) {
        NextRaceWidget(
            uiState = uiState.nextRaceUiState,
            modifier = Modifier
                .fillMaxSize()
                .weight(0.5f),
        )
        RecentRaceResultWidget(
            uiState = uiState.recentResultUiState,
            modifier = Modifier
                .fillMaxSize()
                .weight(0.5f),
        )
    }
}

@Composable
private fun NextRaceWidget(
    uiState: NextRaceUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(top = dimens.spacind_s, start = dimens.spacing_m, end = dimens.spacing_m)
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.home_screen_next_race),
            color = colors.textPrimary,
            style = typography.titleLarge,
            fontFamily = kanitFontFamily,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun RecentRaceResultWidget(
    uiState: RecentResultUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = dimens.spacind_s, horizontal = dimens.spacing_m),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.home_screen_recent_race_result_title),
            color = colors.textPrimary,
            style = typography.titleLarge,
            fontFamily = kanitFontFamily,
            fontWeight = FontWeight.Bold,
        )
        when (uiState) {
            is RecentResultUiState.Success -> {
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp,
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = colors.surface,
                    ),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(all = dimens.spacing_xs),
                    ) {
                        uiState.podium.first?.let {
                            ResultItem(
                                result = it,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(1f),
                            )
                        }

                        if (uiState.podium.first != null && uiState.podium.second != null) {
                            HorizontalDivider(
                                color = colors.dividerPrimary,
                                thickness = dimens.thickness_S,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }

                        uiState.podium.second?.let {
                            ResultItem(
                                result = it,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(1f),
                            )
                        }

                        if (uiState.podium.second != null && uiState.podium.third != null) {
                            HorizontalDivider(
                                color = colors.dividerPrimary,
                                thickness = dimens.thickness_S,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }

                        uiState.podium.third?.let {
                            ResultItem(
                                result = it,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(1f),
                            )
                        }
                    }
                }
            }
            is RecentResultUiState.Failure -> {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Failure",
                )
            }
            is RecentResultUiState.Loading -> {
                CircularIndicatorWidget(
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Composable
private fun ResultItem(
    result: Result,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(
                ResourceProvider.getDriverAvatarByName(driverId = result.driver.id),
            ),
            contentDescription = "driver_avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(dimens.icon_l)
                .clip(RoundedCornerShape(dimens.roundCorner_M))
                .background(colors.avatarBackground),
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = dimens.spacing_m),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    text = result?.time?.time.orEmpty(),
                    color = colors.textPrimary,
                    style = typography.labelSmall,
                    fontFamily = kanitFontFamily,
                    fontWeight = FontWeight.Normal,
                )
                VerticalDivider(
                    color = colors.dividerSecondary,
                    thickness = 1.dp,
                    modifier = Modifier
                        .height(dimens.spacing_m)
                        .padding(horizontal = dimens.spacing_xs),
                )
                Text(
                    text = result?.points.toString(),
                    color = colors.textPrimary,
                    style = typography.labelSmall,
                    fontFamily = kanitFontFamily,
                    fontWeight = FontWeight.Normal,
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = result?.driver?.completeName().orEmpty(),
                color = colors.textPrimary,
                style = typography.bodyLarge,
                fontFamily = kanitFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = result?.constructor?.name.orEmpty(),
                color = colors.textPrimary,
                style = typography.labelMedium,
                fontFamily = kanitFontFamily,
                fontWeight = FontWeight.Medium,
            )
        }

        result?.position?.let { position ->
            MedalWidget(
                position = position,
                size = dimens.icon_m,
            )
        }
    }
}

@Composable
fun MedalWidget(
    position: Int,
    size: Dp,
    modifier: Modifier = Modifier,
) {
    val medalColor = when (position) {
        1 -> colors.medalGold
        2 -> colors.medalSilver
        3 -> colors.medalBronze
        else -> Color.Transparent
    }
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(medalColor),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "$position",
            color = colors.medalText,
            style = typography.headlineSmall,
            fontFamily = kanitFontFamily,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
)
@Composable
fun MainScreenPreview() {
    Formula1Theme {
        HomeScreen(
            uiState = UiState(
                nextRaceUiState = NextRaceUiState.Loading,
                recentResultUiState = RecentResultUiState.Success(
                    podium = mockPodium(),
                ),
            ),
            sideEffect = flow {},
            onAction = {},
        )
    }
}
