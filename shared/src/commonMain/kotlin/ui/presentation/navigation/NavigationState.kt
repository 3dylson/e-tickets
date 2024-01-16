package ui.presentation.navigation

import ui.presentation.main.feed.event.model.Event

data class NavigationState(
    val isUserLoggedIn: Boolean = false,
    val ticketCount: Int = 0,
    val eventOnDetail: Event? = null,
)