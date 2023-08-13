package ui.presentation.navigation

sealed class NavigationEvent {
    data object Login : NavigationEvent()
    data object Logout : NavigationEvent()

}