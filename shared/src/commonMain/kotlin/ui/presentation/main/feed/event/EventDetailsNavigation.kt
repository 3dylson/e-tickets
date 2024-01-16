package ui.presentation.main.feed.event

import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import moe.tlaster.precompose.navigation.path
import ui.presentation.main.feed.event.model.Event
import ui.presentation.main.feed.event.screens.CreditCardInputScreen
import ui.presentation.main.feed.event.screens.EventTicketConfirmationScreen
import ui.presentation.main.feed.event.screens.EventTicketPurchaseScreen
import ui.presentation.navigation.NavigationEvent
import ui.presentation.navigation.NavigationState
import ui.util.deserializeJsonToObject


sealed class EventDetailsDestination(
    val route: String,
    val event: Event? = null,
    val ticketCount: Int? = null,
) {
    data object PurchaseTicketConfirmation :
        EventDetailsDestination("/purchase_ticket_confirmation/{event:.+}/{ticketCount:[0-9]+}") {
        fun route(event: String, ticketCount: Int) =
            "/purchase_ticket_confirmation/$event/$ticketCount"
    }

    data object PurchaseTicket:
        EventDetailsDestination("/purchase_ticket")

    data object PurchaseTicketConcluded :
        EventDetailsDestination("/purchase_ticket_concluded")
}

fun RouteBuilder.eventDetailsGraph(
    navigator: Navigator,
    state: NavigationState,
    onEvent: (NavigationEvent) -> Unit,
) {
    scene(EventDetailsDestination.PurchaseTicketConfirmation.route) { backStackEntry ->
        val eventEncoded: String? = backStackEntry.path<String>("event")
        val ticketCount: String? = backStackEntry.path<String>("ticketCount")

        if (eventEncoded == null || ticketCount == null) {
            return@scene
        }

        val event = deserializeJsonToObject<Event>(eventEncoded)

        EventTicketConfirmationScreen(navigator, state, onEvent, event, ticketCount.toInt()) {
            navigator.navigate(EventDetailsDestination.PurchaseTicket.route)
        }
    }

    scene(EventDetailsDestination.PurchaseTicket.route) {
        //EventTicketPurchaseScreen()
        CreditCardInputScreen()
    }
}