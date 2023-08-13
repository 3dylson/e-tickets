package ui.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import moe.tlaster.precompose.navigation.rememberNavigator
import ui.presentation.main.feed.FeedScreen
import ui.presentation.navigation.NavigationEvent
import ui.presentation.navigation.NavigationState

sealed class MainDestination(
    val route: String,
    val name: String = "",
    val icon: ImageVector? = null,
) {


    data object Main : MainDestination("/main")
    data object Feed : MainDestination("/main/feed", "Feed", Icons.Default.Home)
}

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    mainNavigator: Navigator = rememberNavigator(),
    startDestination: String = MainDestination.Feed.route,
    state: NavigationState,
    onEvent: (NavigationEvent) -> Unit,
    appNavigator: Navigator,
) {
    NavHost(
        navigator = mainNavigator,
        initialRoute = startDestination,
        modifier = modifier,
    ) {
        scene(MainDestination.Feed.route) {
            FeedScreen(
                navigator = appNavigator,
                state = state,
                onEvent = onEvent,
            )
        }
    }
}

fun RouteBuilder.mainGraph(
    navigator: Navigator,
    state: NavigationState,
    onEvent: (NavigationEvent) -> Unit,
) {
    scene(MainDestination.Main.route) {
        MainScreen(
            appNavigator = navigator,
            state = state,
            onEvent = onEvent
        )
    }
}