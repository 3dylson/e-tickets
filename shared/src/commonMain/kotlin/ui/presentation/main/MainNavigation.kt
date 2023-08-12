package ui.presentation.main

import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import ui.presentation.main.feed.event.EventDetailsScreen
import ui.presentation.main.feed.FeedScreen
import ui.presentation.navigation.NavigationEvent
import ui.presentation.navigation.NavigationState
import kotlin.reflect.KFunction1

sealed class MainDestination(
    val route: String,
    val name: String = "",
    val icon: ImageVector? = null,
) {


    data object Main : MainDestination("/main")
    data object Feed : MainDestination("/feed", "Feed", Icons.Default.Home)
    data object EventDetails : MainDestination("/event/{id:[0-9]+}") {
        fun route(id: Int) = "/event/$id"
    }
}

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navigator: Navigator = rememberNavigator(),
    startDestination: String = MainDestination.Feed.route,
    state: NavigationState,
    onEvent: KFunction1<NavigationEvent, Unit>,
) {
    NavHost(
        navigator = navigator,
        initialRoute = startDestination,
        modifier = modifier,
    ) {
        scene(MainDestination.Feed.route) {
            FeedScreen(
                navigator = navigator,
            )
        }
        scene(
            MainDestination.EventDetails.route, navTransition =
            NavTransition(
                createTransition = slideInVertically(initialOffsetY = { it }),
                destroyTransition = slideOutVertically(targetOffsetY = { it }),
                pauseTransition = scaleOut(targetScale = 0.9f),
                resumeTransition = scaleIn(initialScale = 0.9f),
                exitTargetContentZIndex = 1f,
            )
        ) { backStackEntry ->
            val id: String? = backStackEntry.path<String>("id")
            id?.let {
                EventDetailsScreen(
                    id = it,
                    onBackClick = { navigator.goBack() }
                )
            }
        }
    }
}

fun RouteBuilder.mainGraph(
    state: NavigationState,
    onEvent: KFunction1<NavigationEvent, Unit>,
) {
    scene(MainDestination.Main.route) {
        MainScreen(
            state = state,
            onEvent = onEvent
        )
    }
}