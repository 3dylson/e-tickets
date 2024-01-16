package ui.presentation.main.feed

import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.transition.NavTransition
import ui.presentation.main.feed.event.EventDetailsDestination
import ui.presentation.main.feed.event.screens.EventDetailsScreen
import ui.presentation.main.feed.event.eventDetailsGraph
import ui.presentation.navigation.NavigationEvent
import ui.presentation.navigation.NavigationState
import ui.util.serializeObjectToJsonAndUrlEncode

sealed class FeedDestination(val route: String) {
    data object EventDetails : FeedDestination("/event/{id:[0-9]+}") {
        fun route(id: Int) = "/event/$id"
    }
}

fun RouteBuilder.feedGraph(
    navigator: Navigator,
    state: NavigationState,
    onEvent: (NavigationEvent) -> Unit,
) {

    scene(
        FeedDestination.EventDetails.route, navTransition =
        NavTransition(
            createTransition = slideInVertically(initialOffsetY = { it }),
            destroyTransition = slideOutVertically(targetOffsetY = { it }),
            pauseTransition = scaleOut(targetScale = 0.9f),
            resumeTransition = scaleIn(initialScale = 0.9f),
            exitTargetContentZIndex = 1f,
        )
    ) { backStackEntry ->
        val id: String? = backStackEntry.path<String>("id")
        id?.let { id ->
            EventDetailsScreen(
                id = id,
                navigator = navigator,
                state = state,
                onEvent = onEvent,
                onBuyClick = { event, ticketCount ->

                    val encodedEventData = serializeObjectToJsonAndUrlEncode(event)

                    navigator.navigate(
                        EventDetailsDestination.PurchaseTicketConfirmation.route(
                            encodedEventData,
                            ticketCount
                        )
                    )
                }
            )

        }
    }
    eventDetailsGraph(navigator, state, onEvent)
}