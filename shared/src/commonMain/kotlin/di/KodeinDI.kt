package di

import org.kodein.di.DI
import org.kodein.di.bindSingleton

class KodeinDI {

    val contextModule = DI.Module("contextModule") {
        bindSingleton {  }
    }

    val di = DI {
        import(contextModule)
    }
}

object DIFactory {
    val di = KodeinDI().di

}