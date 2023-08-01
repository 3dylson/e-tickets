package ui.util

interface Platform {
    val name: String
    val osName: String
}

expect fun getPlatform(): Platform