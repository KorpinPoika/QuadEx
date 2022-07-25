package di

import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import services.IQuadraticEquationService
import services.QuadraticEquationService
import viewmodel.QuadEqViewModel

fun koinInitialize() {

    val diModule = module {
        singleOf(::QuadraticEquationService) { bind<IQuadraticEquationService>() }
        singleOf(::QuadEqViewModel)
    }

    startKoin {
        printLogger()
        modules(diModule)
    }
}