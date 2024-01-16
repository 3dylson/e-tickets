package ui.util.expectedimpl

actual class Logger actual constructor() {
    actual fun log(message: String, tag: String) {
        // Use Android's built-in logging mechanism (e.g., Log.d)
        android.util.Log.d(tag, message)
    }
}
