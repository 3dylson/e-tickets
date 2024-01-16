package ui.util.expectedimpl

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    override val osName: String = "Android"
    override val isAndroid: Boolean = true
    override val isIOS: Boolean = false
    override val platformType: PlatformType = PlatformType.ANDROID
}

actual fun getPlatform(): Platform = AndroidPlatform()