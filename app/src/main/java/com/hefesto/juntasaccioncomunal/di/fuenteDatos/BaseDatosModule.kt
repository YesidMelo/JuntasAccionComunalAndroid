package com.hefesto.juntasaccioncomunal.di.fuenteDatos

import android.content.Context
import com.hefesto.juntasaccioncomunal.MiAplicacion
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.BaseDatosApp
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.JacDao
import dagger.Module
import dagger.Provides

@Module
class BaseDatosModule {

    @Provides
    fun providesBaseDatosLocal() : BaseDatosApp = BaseDatosApp.traerInstancia(context = MiAplicacion.traerInstancia()!!.applicationContext)

    @Provides
    fun providesJacDao(baseDatosApp: BaseDatosApp): JacDao = baseDatosApp.JacDao()
}