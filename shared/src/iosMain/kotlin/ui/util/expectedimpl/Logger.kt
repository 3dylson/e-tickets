package ui.util.expectedimpl

actual class Logger actual constructor() {
    actual fun log(message: String, tag: String) {
        // Use iOS-specific logging mechanism (e.g., NSLog)
        platform.Foundation.NSLog(tag, message)
    }
}
