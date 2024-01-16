package ui.presentation.main.feed.event.model

import kotlinx.serialization.Serializable


@Serializable
data class Event(
    val eventId: String = "0",
    val eventName: String,
    val eventDate: String,
    val location: String,
    val organizer: String,
    val attendees: List<String> = emptyList(),
    val description: String,
    val imageUrl: String,
    val djs: List<String> = emptyList(),
    val ticketPrice: Double,
)
