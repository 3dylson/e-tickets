package ui.util.presentation

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.StringResource
import ui.util.Strings

@Composable
fun stringResource(id: StringResource, vararg args: Any): String {
    return "TODO()" //Strings(LocalContext.current).get(id, args.toList())
}