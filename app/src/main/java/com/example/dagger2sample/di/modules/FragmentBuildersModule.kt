package com.example.dagger2sample.di.modules


import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.example.dagger2sample.ui.fragments.MovieListFragment


@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeImagesListFragment(): MovieListFragment


}
