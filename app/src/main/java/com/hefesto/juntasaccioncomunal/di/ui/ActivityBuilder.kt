package com.hefesto.juntasaccioncomunal.di.ui

import com.hefesto.juntasaccioncomunal.di.ui.modules.SplashActivityModule
import com.hefesto.juntasaccioncomunal.ui.activities.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {


    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract  fun contributeSplashActivity() : SplashActivity
}