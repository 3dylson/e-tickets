package ui.presentation.start

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.PopUpTo
import moe.tlaster.precompose.navigation.rememberNavigator
import ui.presentation.main.MainDestination
import ui.presentation.main.mainGraph
import ui.presentation.navigation.NavigationEvent
import ui.presentation.navigation.NavigationState
import ui.presentation.start.login.LoginScreen
import kotlin.reflect.KFunction1

sealed class StartDestination(val route: String) {
    data object Start : StartDestination("/start")
    data object Login : StartDestination("/login")
    data object Register : StartDestination("/register")

}

@Composable
fun StartNavigation(
    modifier: Modifier = Modifier,
    navigator: Navigator = rememberNavigator(),
    startDestination: String = StartDestination.Start.route,
    state: NavigationState,
    onEvent: KFunction1<NavigationEvent, Unit>,
) {
    NavHost(
        navigator = navigator,
        initialRoute = startDestination,
        modifier = modifier,
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
                    navigator.navigate(MainDestination.Main.route, NavOptions(popUpTo = PopUpTo.Route("", true)))
                },
                onForgotPasswordClick = {

                },
            )
        }

        mainGraph(state, onEvent)

    }
}