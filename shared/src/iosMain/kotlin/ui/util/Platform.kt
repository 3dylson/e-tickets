package ui.util

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val osName: String = "iOS"
    override val isAndroid: Boolean = false
    override val isIOS: Boolean = true
    override val platformType: PlatformType = PlatformType.IOS
}

actual fun getPlatform(): Platform = IOSPlatform()