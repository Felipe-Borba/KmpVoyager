import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.named
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.withOptions
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

// koin seems to have annotations based injection called ksp but it's very complicated to setup
// font https://www.youtube.com/watch?v=A6Ksf3ylEsM&t=259s
val di = module {
    single<SampleInterface>(createdAtStart = true) {
        ClassA()
    }
    singleOf(::ClassA) withOptions {
        createdAtStart()
    }
    single<SampleInterface> {
        ClassC()
    } withOptions {
        createdAtStart()
        named("ABC")
    }
    factory {
        ClassB(sampleInterface = get(qualifier("ABC")))
    }

//    factory {
//        ScreenViewModel()
//    }
//    or
    factoryOf(::ScreenViewModel)
}