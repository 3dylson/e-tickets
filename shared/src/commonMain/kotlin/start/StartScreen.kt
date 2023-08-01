package start

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import dev.icerock.moko.resources.StringResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import pt.etickets.SharedRes
import ui.util.Strings
import ui.util.presentation.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun StartFragment() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                enabled = true,
                state = ScrollState(0),
                reverseScrolling = false,
                flingBehavior = null,
            )

    ) {
        Surface(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(ZeroCornerSize),
            tonalElevation = 0.dp,
        ) {
            Image(
                painter = painterResource("compose-multiplatform.xml"),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(id = SharedRes.strings.app_name),
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "TODO",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* Handle Login Button Click */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Login",
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { /* Handle Register Button Click */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.primary,
            )
        ) {
            Text(
                text = "Register",
            )
        }
    }
}
