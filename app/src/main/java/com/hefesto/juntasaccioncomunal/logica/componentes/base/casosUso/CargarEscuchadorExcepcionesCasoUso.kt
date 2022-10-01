package com.hefesto.juntasaccioncomunal.logica.componentes.base.casosUso

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.base.repositorios.BaseRepositorio
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import javax.inject.Inject

interface CargarEscuchadorExcepcionesCasoUso {
    fun invoke() : MutableLiveData<LogicaExcepcion?>
}

class CargarEscuchadorExcepcionesCasoUsoImpl constructor(
    @JvmField @Inject var baseRepositorio: BaseRepositorio
) : CargarEscuchadorExcepcionesCasoUso {

    override fun invoke(): MutableLiveData<LogicaExcepcion?> = baseRepositorio.traerExcepcionesLiveData()

}