import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import ui.presentation.main.MainDestination
import ui.presentation.navigation.NavigationViewModel
import ui.presentation.start.StartDestination
import ui.presentation.start.StartNavigation
import ui.theme.AppTheme

@Composable
fun App() {
    AppTheme {

        val viewModel = getViewModel(
            key = "navigation",
            factory = viewModelFactory {
                NavigationViewModel()
            }
        )

        val state by viewModel.state.collectAsState()

        val startDestination = when {
            state.isUserLoggedIn -> {
                MainDestination.Main.route
            }

            else -> {
                StartDestination.Start.route
            }
        }

        StartNavigation(
            state = state,
            onEvent = viewModel::onEvent,
            startDestination = startDestination
        )
    }
}