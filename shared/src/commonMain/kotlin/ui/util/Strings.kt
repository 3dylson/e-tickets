package ui.util

import dev.icerock.moko.resources.StringResource

expect class Strings() {
    fun get(id: StringResource, args: List<Any>, context: Any? = null): String
}