package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.configuracionAfiliadoEnDirectiva

import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeRepositorio
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoEnDirectivaModificadoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ActualizarAfiliadoEnDirectivaCasoUso {
    fun invoke(afiliadoEnDirectivaModificadoModel: AfiliadoEnDirectivaModificadoModel): Flow<Boolean?>
}

class ActualizarAfiliadoEnDirectivaCasoUsoImpl constructor(
    @JvmField @Inject var homeRepositorio: HomeRepositorio
) : ActualizarAfiliadoEnDirectivaCasoUso {

    override fun invoke(afiliadoEnDirectivaModificadoModel: AfiliadoEnDirectivaModificadoModel): Flow<Boolean?>
    = homeRepositorio.actualizarAfiliadoEnDirectiva(afiliadoEnDirectivaModificadoModel= afiliadoEnDirectivaModificadoModel)

}