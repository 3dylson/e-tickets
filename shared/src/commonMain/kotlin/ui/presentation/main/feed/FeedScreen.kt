package ui.presentation.main.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.presentation.main.feed.event.model.Event
import ui.presentation.navigation.NavigationEvent
import ui.presentation.navigation.NavigationState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(navigator: Navigator, state: NavigationState, onEvent: (NavigationEvent) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { FeedScreenTopBar() },
    ) { innerPadding ->
        innerPadding
        FeedScreenContent(innerPadding, navigator, state, onEvent)
    }
}

@Composable
private fun FeedScreenContent(
    innerPadding: PaddingValues,
    navigator: Navigator,
    state: NavigationState,
    onEvent: (NavigationEvent) -> Unit,
) {
    val eventFeedItems = createMockEventFeedItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        items(eventFeedItems.size) { index ->
            val eventItem = eventFeedItems[index]
            EventFeedCard(
                eventName = eventItem.eventName,
                eventDate = eventItem.eventDate,
                location = eventItem.location,
                imageUrl = eventItem.imageUrl,
                description = eventItem.description,
                onClickEvent = {
                    navigator.navigate(FeedDestination.EventDetails.route(eventItem.eventId.toInt()))
                }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FeedScreenTopBar() {
    TopAppBar(
        title = { Text(text = "e-Tickets") },
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun EventFeedCard(
    eventName: String,
    eventDate: String,
    location: String,
    imageUrl: String,
    description: String,
    onClickEvent: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClickEvent() },
        elevation = CardDefaults.elevatedCardElevation(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        )
    ) {
        Column(
            modifier = Modifier
                .background(Color.Transparent).padding(16.dp)
        ) {
            // Event image
            Image(
                painter = painterResource("compose-multiplatform.xml"),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Event name
            Text(text = eventName, style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(8.dp))

            // Event date and location
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = eventDate, style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = location, style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Event description
            Text(text = description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}


fun createMockEventFeedItems(count: Int = 5): List<Event> {
    val mockEvent = Event(
        eventName = "Sample Event",
        eventDate = "January 1, 2023",
        location = "Mock Location",
        imageUrl = "https://example.com/image.jpg",
        description = "This is a sample event description.",
        organizer = "Mock Organizer",
    )
    return List(count) { mockEvent }
}