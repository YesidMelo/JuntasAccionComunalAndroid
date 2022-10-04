package com.hefesto.juntasaccioncomunal.logica.componentes.splash.casosUso

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
        iniciarCarga()
        return cargo
    }

    //metodos privados
    private fun iniciarCarga() {
        GlobalScope.launch {
            delay(5000)
            splashRepositorio.iniciarEscuchadorExcepciones()
            splashRepositorio.cargarConstantesEnDB()
            cargo.postValue(true)
        }
    }
    //endregion

}