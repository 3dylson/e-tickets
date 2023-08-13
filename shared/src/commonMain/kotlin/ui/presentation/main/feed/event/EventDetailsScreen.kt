package ui.presentation.main.feed.event

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.presentation.main.feed.createMockEventFeedItems
import ui.presentation.main.feed.event.model.Event
import ui.presentation.navigation.NavigationEvent
import ui.presentation.navigation.NavigationState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun EventDetailsScreen(
    id: String,
    navigator: Navigator,
    state: NavigationState,
    onEvent: (NavigationEvent) -> Unit,
) {
    val eventDetails = getEventDetailsById(id) // Replace with actual data retrieval

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detalhes do evento") },
                navigationIcon = {
                    IconButton(onClick = { navigator.goBack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Event image
            Image(
                painter = painterResource("compose-multiplatform.xml"),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Event details
            Text(
                text = eventDetails.eventName,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = eventDetails.eventDate,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = eventDetails.location,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = eventDetails.description,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.weight(1f))

            // Buy ticket button
            Button(
                onClick = { /* Handle buy ticket click */ },
                contentPadding = PaddingValues(vertical = 16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(text = "Comprar Ticket")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

fun getEventDetailsById(id: String): Event {
    // Replace with actual data retrieval logic based on the provided id
    // For example, fetch details from a repository or database
    return createMockEventFeedItems()[0]
}
