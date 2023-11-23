package it.battagliandrea.formula1.feature.main.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.BackStackModel
import com.bumble.appyx.components.backstack.operation.push
import com.bumble.appyx.components.backstack.ui.fader.BackStackFader
import com.bumble.appyx.navigation.composable.AppyxComponent
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.node.ParentNode
import com.bumble.appyx.navigation.node.node
import it.battagliandrea.formula1.feature.main.navigation.MainNavTarget.Results
import it.battagliandrea.formula1.feature.main.navigation.MainNavTarget.Schedule
import it.battagliandrea.formula1.feature.main.navigation.MainNavTarget.Standings

class MainNode(
    buildContext: BuildContext,
    private val backStack: BackStack<MainNavTarget> = BackStack(
        model = BackStackModel(
            initialTarget = Schedule,
            savedStateMap = buildContext.savedStateMap,
        ),
        visualisation = { BackStackFader(it) },
    ),
) : ParentNode<MainNavTarget>(
    appyxComponent = backStack,
    buildContext = buildContext,
) {
    override fun resolve(interactionTarget: MainNavTarget, buildContext: BuildContext): Node =
        when (interactionTarget) {
            Schedule -> node(buildContext) { Text(text = "Placeholder for SCHEDULE") }
            Results -> node(buildContext) { Text(text = "Placeholder for RESULT") }
            Standings -> node(buildContext) { Text(text = "Placeholder for STANDINGS") }
        }

    @Composable
    override fun View(modifier: Modifier) {
        Column(
            modifier = modifier,
        ) {
            // Let's include the elements of our component into the composition
            AppyxComponent(
                appyxComponent = backStack,
                modifier = Modifier.weight(0.9f),
            )

            // Let's also add some controls so we can test it
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f),
            ) {
                TextButton(onClick = { backStack.push(Schedule) }) {
                    Text(text = "SCHEDULE")
                }
                TextButton(onClick = { backStack.push(Results) }) {
                    Text(text = "RESULTS")
                }
                TextButton(onClick = { backStack.push(Standings) }) {
                    Text(text = "STANDINGS")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeNodePreview() {
    MainNode(
        buildContext = BuildContext.root(null),
    ).Compose()
}
