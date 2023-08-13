package ui.presentation.main

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator
import ui.presentation.navigation.NavigationEvent
import ui.presentation.navigation.NavigationState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: NavigationState,
    onEvent: (NavigationEvent) -> Unit,
    appNavigator: Navigator,
) {
    val mainNavigator = rememberNavigator()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { bottomBar(mainNavigator) },
    ) { innerPadding ->
        innerPadding
        MainNavigation(
            mainNavigator = mainNavigator,
            appNavigator = appNavigator,
            state = state,
            onEvent = onEvent
        )
    }
}

@Composable
private fun bottomBar(
    navigator: Navigator,
) {
    BottomBar(navigator = navigator)
}

@Composable
fun BottomBar(
    navigator: Navigator,
) {

    val screens = listOf(
        MainDestination.Feed,
    )

    val currentEntry = navigator.currentEntry.collectAsState(null)
    val currentRoute = currentEntry.value?.route?.route ?: ""

    NavigationBar {

        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentRoute,
                navigator = navigator
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: MainDestination,
    currentDestination: String,
    navigator: Navigator,
) {
    val isCurrentDestination = currentDestination == screen.route

    NavigationBarItem(
        selected = isCurrentDestination,
        onClick = {
            if (isCurrentDestination) {
                return@NavigationBarItem
            }
            navigator.navigate(screen.route, NavOptions(launchSingleTop = true))
        },
        label = { Text(screen.name) },
        icon = { screen.icon?.let { Icon(it, contentDescription = null) } }

    )
}