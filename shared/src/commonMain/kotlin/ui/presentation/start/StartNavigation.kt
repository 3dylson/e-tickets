package ui.presentation.start

import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.PopUpTo
import moe.tlaster.precompose.navigation.RouteBuilder
import ui.presentation.main.MainDestination
import ui.presentation.navigation.NavigationEvent
import ui.presentation.navigation.NavigationState
import ui.presentation.start.login.LoginScreen

sealed class StartDestination(val route: String) {
    data object Start : StartDestination("/start")
    data object Login : StartDestination("/login")
    data object Register : StartDestination("/register")

}

fun RouteBuilder.startGraph(
    navigator: Navigator,
    state: NavigationState,
    onEvent: (NavigationEvent) -> Unit,
) {
    scene(StartDestination.Start.route) {
        StartScreen(
            onLoginClick = {
                navigator.navigate(StartDestination.Login.route)
            },
            onRegisterClick = {
                navigator.navigate(StartDestination.Register.route)
            },
        )
    }
    scene(StartDestination.Login.route) {
        LoginScreen(
            onCloseClick = {
                navigator.navigate(StartDestination.Start.route)
            },
            onLoginClick = {
                navigator.navigate(
                    MainDestination.Main.route,
                    NavOptions(popUpTo = PopUpTo.Route("", true))
                )
            },
            onForgotPasswordClick = {

            },
        )
    }
}