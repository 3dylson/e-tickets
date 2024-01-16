package ui.presentation.main.feed.event.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EventTicketPurchaseScreen() {
    var cardNumber by remember { mutableStateOf("") }
    var cardName by remember { mutableStateOf("") }
    var cardExpiration by remember { mutableStateOf("") }
    var cardCVC by remember { mutableStateOf("") }
    var otherPaymentSelected by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val density = LocalDensity.current.density

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top section - Card information
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Card Information",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(16.dp))
                BasicTextField(
                    value = cardNumber,
                    onValueChange = { cardNumber = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            keyboardController?.hide()
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                )
                Spacer(modifier = Modifier.height(16.dp))
                // Add more input fields for card name, expiration date, and CVC here.
            }
        }

        
        // Bottom section - Other payment methods
        OtherPayments()
    }
}

@Composable
private fun OtherPayments() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            //.weight(1f)
            .padding(8.dp)
    ) {
        Text(
            text = "Other Payment Methods",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            // Button to select other payment methods (e.g., PayPal)
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(8.dp)
            ) {
                Text(
                    text = "Pay with PayPal",
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun CreditCardInputScreen() {
    var cardNumber by remember { mutableStateOf("") }
    var cardHolder by remember { mutableStateOf("") }
    var cardExpiration by remember { mutableStateOf("") }
    var cardCVV by remember { mutableStateOf("") }
    //val selectionState = remember { SelectionState() }

    val cardWidth = 350.dp
    val cardHeight = 200.dp

    val density = LocalDensity.current.density

    val cardFocusRequester = FocusRequester()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Credit Card Information",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.large)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp)
                    .focusRequester(cardFocusRequester)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Card Number",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Image(
                            painter = org.jetbrains.compose.resources.painterResource("ic-google.xml"),
                            contentDescription = "Card Chip",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    SelectionContainer {
                        BasicTextField(
                            value = cardNumber,
                            onValueChange = {
                                cardNumber = it.take(16)
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { cardFocusRequester.requestFocus() },
                                //onNext = { selectionState.selectAll() }
                            ),
                            textStyle = TextStyle(
                                fontSize = 20.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                /*.onSizeChanged {
                                    val density = density
                                    val cardWidthInPixels = cardWidth.toPx() - 32 * density
                                    val textWidthInPixels = it.width * density
                                    if (textWidthInPixels > cardWidthInPixels) {
                                        //selectionState.stopSelection()
                                    }
                                }*/
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Card Holder",
                                style = MaterialTheme.typography.bodySmall,
                            )
                            Text(
                                text = cardHolder.take(20),
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                        Column {
                            Text(
                                text = "Expires",
                                style = MaterialTheme.typography.bodySmall,
                            )
                            Text(
                                text = cardExpiration.take(5),
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "CVV",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Text(
                            text = cardCVV.take(3),
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
            }
        }

        OutlinedTextField(
            value = cardHolder,
            onValueChange = {
                cardHolder = it.take(20)
            },
            label = { Text(text = "Card Holder") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = cardExpiration,
                onValueChange = {
                    cardExpiration = it.take(5)
                },
                label = { Text(text = "Expires") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            OutlinedTextField(
                value = cardCVV,
                onValueChange = {
                    cardCVV = it.take(3)
                },
                label = { Text(text = "CVV") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )
        }

        Button(
            onClick = { /* Handle form submission here */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(text = "Submit")
        }
    }
}



