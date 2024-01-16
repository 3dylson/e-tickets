package ui.util.expectedimpl

import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

actual fun urlEncode(input: String): String {
    try {
        return URLEncoder.encode(input,  StandardCharsets.UTF_8.toString())
    } catch (e: UnsupportedEncodingException) {
        throw RuntimeException("URL encoding failed", e)
    }
}

actual fun urlDecode(input: String): String {
    return URLDecoder.decode(input, StandardCharsets.UTF_8.toString())
}