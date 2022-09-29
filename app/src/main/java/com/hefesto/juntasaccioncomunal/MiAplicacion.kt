package com.hefesto.juntasaccioncomunal

import com.hefesto.juntasaccioncomunal.di.DaggerAplicacionComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MiAplicacion : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        instancia = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAplicacionComponent.builder().create(this)

    companion object {
        private var instancia : MiAplicacion? = null
        fun traerInstancia() = instancia
    }
}