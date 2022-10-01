package com.hefesto.juntasaccioncomunal.logica.componentes.splash.casosUso

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.*
import javax.inject.Inject

interface PrecargaAplicacionFinalizadaCasoUso {
    fun invoke() : MutableLiveData<Boolean>
}

class PrecargaAplicacionFinalizadaCasoUsoImpl(
    @JvmField @Inject var splashRepositorio: SplashRepositorio,
) : PrecargaAplicacionFinalizadaCasoUso {

    //region variables
    private var cargo = MutableLiveData<Boolean>()
    private var cargando = false
    //endregion

    override fun invoke(): MutableLiveData<Boolean> {
        if (cargando) return cargo
        splashRepositorio.iniciarEscuchadorExcepciones()
        Handler().postDelayed(
        {
            cargo.value = true
        }, 5000)
        return cargo
    }
}