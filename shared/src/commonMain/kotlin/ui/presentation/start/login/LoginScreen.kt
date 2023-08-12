package ui.presentation.start.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import ui.presentation.components.BottomTextButton
import ui.presentation.components.GoogleSignInButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onCloseClick: () -> Unit, onLoginClick: () -> Unit, onForgotPasswordClick: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { LoginScreenTopBar(onCloseClick) },
        content = { innerPadding ->
            LoginScreenContent(innerPadding, onLoginClick, onForgotPasswordClick)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginScreenTopBar(onCloseClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "") },
        navigationIcon = {
            IconButton(onClick = onCloseClick) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null //TODO
                )
            }
        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun LoginScreenContent(
    innerPadding: PaddingValues,
    onLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp).verticalScroll(rememberScrollState())
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Bem vindo de volta!",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 32.dp, bottom = 8.dp)
                    .align(Alignment.Start)
            )

            Text(
                text = "Digite seu e-mail e password",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = 16.dp).align(Alignment.Start)
            )

            var username by remember { mutableStateOf("") }
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email
                ),
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            var password by remember { mutableStateOf("") }
            var passwordVisibility by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(
                            imageVector = if (passwordVisibility) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = null //TODO
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp)
            )


            Button(
                onClick = onLoginClick,
                enabled = username.isNotEmpty() && password.isNotEmpty(),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                Text(text = "Log in")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = {},
                modifier = Modifier.align(Alignment.End),
                contentPadding = PaddingValues(all = 8.dp)
            ) {
                Text(text = "Esqueceu sua senha?")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "OU",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            GoogleSignInButton({/*TODO*/ })

            Spacer(modifier = Modifier.height(16.dp))

            /*Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                Text(text = "Entrar com o Facebook")
            }*/

            BottomTextButton(
                text = "Não tem uma conta?",
                btnText = "Cadastre-se",
                onClick = {},
            )
        }
    }
}
