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
        }
    }

    private fun updateStateOnLogin() {
        _state.update { it.copy(isUserLoggedIn = true) }
    }

    private fun updateStateOnLogout() {
        _state.update { it.copy(isUserLoggedIn = false) }
    }
}