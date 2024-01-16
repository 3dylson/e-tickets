package ui.util.expectedimpl

expect class Logger() {
    fun log(message: String, tag: String = "MyApp")
}
