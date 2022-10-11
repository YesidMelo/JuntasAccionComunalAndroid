package com.hefesto.juntasaccioncomunal.logica.componentes.home.casosUso.registroAfiliado

import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.HomeRepositorio
import com.hefesto.juntasaccioncomunal.logica.modelos.home.DatosBasicosAfiliadoActualizarRegistrarInformacionModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TraerListaAfiliadosRegistroActualizacionCasoUso {
    fun invoke() : Flow<List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>?>
}

class TraerListaAfiliadosRegistroActualizacionCasoUsoImpl constructor(
    @JvmField @Inject var homeRepositorio: HomeRepositorio
) : TraerListaAfiliadosRegistroActualizacionCasoUso {

    override fun invoke(): Flow<List<DatosBasicosAfiliadoActualizarRegistrarInformacionModel>?>
    = homeRepositorio.traerListaAfiliadosRegistroActualizacion()
}