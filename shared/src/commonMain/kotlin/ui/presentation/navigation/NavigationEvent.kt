package ui.presentation.navigation

import ui.presentation.main.feed.event.model.Event

sealed class NavigationEvent {
    data object Login : NavigationEvent()
    data object Logout : NavigationEvent()
    data class BuyTicketClicked(val event: Event, val ticketCount: Int) : NavigationEvent()

}