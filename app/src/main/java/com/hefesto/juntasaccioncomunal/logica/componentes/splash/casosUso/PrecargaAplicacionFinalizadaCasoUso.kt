package com.hefesto.juntasaccioncomunal.logica.componentes.splash.casosUso

import android.os.Handler
import androidx.lifecycle.MutableLiveData

interface PrecargaAplicacionFinalizadaCasoUso {
    fun invoke() : MutableLiveData<Boolean>
}

class PrecargaAplicacionFinalizadaCasoUsoImpl : PrecargaAplicacionFinalizadaCasoUso {
    //region variables
    private var cargo = MutableLiveData<Boolean>()
    private var cargando = false
    //endregion

    override fun invoke(): MutableLiveData<Boolean> {
        if (cargando) return cargo
        Handler().postDelayed(
        {
            cargo.value = true
        }, 5000)
        return cargo
    }
}