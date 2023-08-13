package ui.presentation.main.feed.event

import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import ui.presentation.navigation.NavigationEvent
import ui.presentation.navigation.NavigationState


sealed class EventDetailsDestination(
    val route: String,
) {

}

fun RouteBuilder.eventDetailsGraph(
    navigator: Navigator,
    state: NavigationState,
    onEvent: (NavigationEvent) -> Unit,
) {

}