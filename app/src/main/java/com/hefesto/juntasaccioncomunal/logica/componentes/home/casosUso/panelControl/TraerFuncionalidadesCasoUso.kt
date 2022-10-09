package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.panelControl

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeRepositorio
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FuncionesRolApp
import javax.inject.Inject

interface TraerFuncionalidadesCasoUso {
    fun invoke() : MutableLiveData<List<FuncionesRolApp>>
}

class TraerFuncionalidadesCasoUsoImpl constructor(
    @JvmField @Inject var homeRepositorio: HomeRepositorio
) : TraerFuncionalidadesCasoUso {

    override fun invoke() = homeRepositorio.traerFuncionalidadesRol()

}