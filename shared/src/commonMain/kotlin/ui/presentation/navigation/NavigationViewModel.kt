package ui.presentation.navigation

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class NavigationViewModel() : ViewModel() {


    private val _state = MutableStateFlow(NavigationState())
    val state = _state

    fun onEvent(event: NavigationEvent) {
        when (event) {
            NavigationEvent.Login -> {
                _state.value = NavigationState(isUserLoggedIn = true)
            }

            NavigationEvent.Logout -> {
                _state.value = NavigationState(isUserLoggedIn = false)
            }
        }
    }
}