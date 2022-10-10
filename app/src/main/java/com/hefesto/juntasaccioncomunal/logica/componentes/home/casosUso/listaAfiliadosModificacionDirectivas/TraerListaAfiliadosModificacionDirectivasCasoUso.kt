package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.listaAfiliadosModificacionDirectivas

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeRepositorio
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoParaModificacionDirectivaModel
import javax.inject.Inject

interface TraerListaAfiliadosModificacionDirectivasCasoUso {
    fun invoke(): MutableLiveData<List<AfiliadoParaModificacionDirectivaModel>>
}

class TraerListaAfiliadosModificacionDirectivasCasoUsoImpl constructor(
    @JvmField @Inject var homeRepositorio: HomeRepositorio
) : TraerListaAfiliadosModificacionDirectivasCasoUso {

    override fun invoke(): MutableLiveData<List<AfiliadoParaModificacionDirectivaModel>> = homeRepositorio.traerListaAfiliadosModificacionRolDirectiva()
}