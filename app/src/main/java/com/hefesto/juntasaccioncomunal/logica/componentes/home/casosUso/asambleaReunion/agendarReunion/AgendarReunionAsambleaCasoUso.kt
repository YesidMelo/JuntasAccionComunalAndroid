package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.asambleaReunion.agendarReunion

import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeRepositorio
import com.hefesto.juntasaccioncomunal.logica.modelos.home.reunionAsambleas.agendarReunion.DetalleReunionAAgendarModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface AgendarReunionAsambleaCasoUso {
    fun invoke(detalleReunionAAgendarModel: DetalleReunionAAgendarModel): Flow<Boolean>
}

class AgendarReunionAsambleaCasoUsoImpl constructor(
    @JvmField @Inject var homeRepositorio: HomeRepositorio
) : AgendarReunionAsambleaCasoUso {

    override fun invoke(detalleReunionAAgendarModel: DetalleReunionAAgendarModel): Flow<Boolean> = flow {
        emit(false)
        HelperValidacionesReunionAAgendar(detalleReunionAAgendarModel = detalleReunionAAgendarModel)
            .validarModelo()
            .validarTitulo()
            .validarTipoReunion()
            .validarFecha()
            .validarHora()
            .validarListaPuntos()
            .validarConvocantes()
            .validarSitio()
        homeRepositorio
            .agendarReunionAsamblea(detalleReunionAAgendarModel = detalleReunionAAgendarModel)
            .collect{ emit(it) }
    }

}