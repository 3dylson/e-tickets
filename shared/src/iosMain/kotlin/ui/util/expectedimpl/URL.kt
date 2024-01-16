package ui.util.expectedimpl

import platform.Foundation.NSCharacterSet
import platform.Foundation.NSString
import platform.Foundation.URLQueryAllowedCharacterSet
import platform.Foundation.create
import platform.Foundation.stringByAddingPercentEncodingWithAllowedCharacters
import platform.Foundation.stringByRemovingPercentEncoding

actual fun urlEncode(input: String): String {
    // Step 1: URL-encode the entire JSON string
    val nsInput = NSString.create(string = input)
    val allowedCharacterSet = NSCharacterSet.URLQueryAllowedCharacterSet()
    val urlEncodedString = nsInput.stringByAddingPercentEncodingWithAllowedCharacters(allowedCharacterSet)
        ?: throw RuntimeException("URL encoding failed")

    //TODO: iOS decoding presents different results than Android decoding

/*    // Step 2: Replace specific characters with their percent-encoded values
    val replacedString = urlEncodedString
        .replace(" ", "+") // Replace spaces with "+"
        .replace("\"", "%22") // Replace double quotes with "%22"
        .replace(":", "%3A") // Replace colons with "%3A"
        .replace(",", "%2C") // Replace commas with "%2C"*/

    return urlEncodedString
}


actual fun urlDecode(input: String): String {
    val nsInput = NSString.create(string = input)
    val decodedString = nsInput.stringByRemovingPercentEncoding

    return decodedString ?: throw RuntimeException("URL decoding failed")
}