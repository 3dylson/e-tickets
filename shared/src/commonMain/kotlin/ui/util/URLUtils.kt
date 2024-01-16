package ui.util

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ui.util.expectedimpl.Logger
import ui.util.expectedimpl.urlDecode
import ui.util.expectedimpl.urlEncode

/**
 * Serializes an object to a JSON-encoded string and URL-encodes the result.
 *
 * This function serializes the provided object into a JSON string using Kotlin's kotlinx.serialization library
 * and then URL-encodes the resulting JSON string. The URL-encoded string can be used in various contexts,
 * such as HTTP requests, as query parameters in URLs, or to pass data between Screens/Routes.
 *
 * @param input The object to be serialized and URL-encoded.
 * @return A URL-encoded string representing the serialized JSON data.
 * @throws kotlinx.serialization.SerializationException If there is an issue with the serialization process.
 */
inline fun <reified T : Any> serializeObjectToJsonAndUrlEncode(input: T): String {
    val jsonToString = Json.encodeToString(input)
    Logger().log("jsonToString: $jsonToString")
    return urlEncode(jsonToString)
}


/**
 * Deserializes a JSON string into an object of the specified type.
 *
 * This inline function uses Kotlin's kotlinx.serialization library to deserialize
 * a JSON string into an object of type 'T'. The 'reified' type parameter 'T' allows
 * you to work with the type 'T' at runtime. It is assumed that the input JSON string
 * is URL-encoded, and the 'ignoreUnknownKeys' property of the JSON configuration is set to 'true'.
 *
 * @param input The JSON string to be deserialized.
 * @return An object of the specified type 'T' representing the deserialized JSON data.
 * @throws kotlinx.serialization.SerializationException If there is an issue with the deserialization process.
 */
inline fun <reified T> deserializeJsonToObject(input: String): T {
    val json = Json { ignoreUnknownKeys = true }
    val decodedString = urlDecode(input)
    Logger().log("decodedString: $decodedString")
    return json.decodeFromString(decodedString)
}
