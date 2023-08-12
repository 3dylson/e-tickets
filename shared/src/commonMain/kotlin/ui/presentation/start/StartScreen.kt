package ui.presentation.start

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun StartScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(ZeroCornerSize),
                tonalElevation = 0.dp,
            ) {
                Image(
                    painter = painterResource("compose-multiplatform.xml"),
                    contentDescription = "background image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "e-tickets", //stringResource(SharedRes.strings.app_name),
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { onLoginClick() },
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 16.dp),
            ) {
                Text(
                    text = "Login",
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onRegisterClick() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primaryContainer),
                contentPadding = PaddingValues(vertical = 16.dp),
            ) {
                Text(
                    text = "Nova conta",
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

/*@Composable
fun stringResource(id: StringResource, vararg args: Any): String {
    return when (getPlatform().platformType) {
        PlatformType.ANDROID -> {
            ""
        } //Strings(LocalContext.current).get(id, args.toList())
        PlatformType.IOS -> {
            Strings().get(id, args.toList(), null)
        }
    }
}*/
