package ui.util.expectedimpl

interface Platform {
    val name: String
    val osName: String
    val isAndroid: Boolean
    val isIOS: Boolean
    val platformType: PlatformType
}

enum class PlatformType {
    ANDROID,
    IOS
}

expect fun getPlatform(): Platform
