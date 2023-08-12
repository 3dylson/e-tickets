package ui.presentation.navigation

sealed interface NavigationEvent {
    object Login : NavigationEvent
    object Logout : NavigationEvent
}