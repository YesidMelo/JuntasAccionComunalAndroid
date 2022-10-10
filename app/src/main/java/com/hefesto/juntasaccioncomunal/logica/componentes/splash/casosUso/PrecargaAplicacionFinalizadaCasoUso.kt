package com.hefesto.juntasaccioncomunal.logica.componentes.splash.casosUso

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.splash.repositorios.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface PrecargaAplicacionFinalizadaCasoUso {
    fun invoke() : Flow<Boolean>
}

class PrecargaAplicacionFinalizadaCasoUsoImpl(
    @JvmField @Inject var splashRepositorio: SplashRepositorio,
) : PrecargaAplicacionFinalizadaCasoUso {

    //region variables
    private var cargando = false
    //endregion

    override fun invoke(): Flow<Boolean> = flow {
        if (cargando) return@flow
        iniciarCarga()
        emit(true)
        return@flow
    }

    //metodos privados
    private suspend fun iniciarCarga() {
        delay(5000)
        splashRepositorio.iniciarEscuchadorExcepciones()
        splashRepositorio.cargarConstantesEnDB()
    }
    //endregion

}