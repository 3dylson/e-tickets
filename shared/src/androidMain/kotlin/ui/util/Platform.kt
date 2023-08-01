package ui.util

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    override val osName: String = "Android"
}

actual fun getPlatform(): Platform = AndroidPlatform()