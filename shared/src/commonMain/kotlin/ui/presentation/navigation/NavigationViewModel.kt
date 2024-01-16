package ui.presentation.navigation

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class NavigationViewModel : ViewModel() {


    private val _state = MutableStateFlow(NavigationState())
    val state: StateFlow<NavigationState> = _state

    fun onEvent(event: NavigationEvent) {
        when (event) {
            is NavigationEvent.Login -> updateStateOnLogin()
            is NavigationEvent.Logout -> updateStateOnLogout()
            is NavigationEvent.BuyTicketClicked -> updateStateTicketEvent(event)
        }
    }

    private fun updateStateTicketEvent(event: NavigationEvent.BuyTicketClicked) {
        _state.update { it.copy(eventOnDetail = event.event, ticketCount = event.ticketCount) }
    }

    private fun updateStateOnLogin() {
        _state.update { it.copy(isUserLoggedIn = true) }
    }

    private fun updateStateOnLogout() {
        _state.update { it.copy(isUserLoggedIn = false) }
    }
}