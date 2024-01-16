package ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator
import ui.presentation.main.feed.feedGraph
import ui.presentation.main.mainGraph
import ui.presentation.start.StartDestination
import ui.presentation.start.startGraph

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navigator: Navigator = rememberNavigator(),
    startDestination: String = StartDestination.Start.route,
    state: NavigationState,
    onEvent: (NavigationEvent) -> Unit,
) {


    NavHost(
        navigator = navigator,
        initialRoute = startDestination,
        modifier = modifier,
    ) {
        startGraph(navigator, state, onEvent)
        mainGraph(navigator, state, onEvent)
        feedGraph(navigator, state, onEvent)


    }
}