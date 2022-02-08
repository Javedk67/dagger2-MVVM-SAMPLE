package com.example.dagger2sample.di.modules


import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.example.dagger2sample.ui.MainActivity

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(
        modules = [
            FragmentBuildersModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity

}
