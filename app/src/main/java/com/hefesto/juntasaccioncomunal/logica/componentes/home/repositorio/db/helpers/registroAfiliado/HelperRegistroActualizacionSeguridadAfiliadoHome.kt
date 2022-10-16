package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.registroAfiliado

import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.CompiladoInformacionAfiliadoParaRegistroModel

interface HelperRegistroActualizacionSeguridadAfiliadoHome {
    suspend fun registrarSeguridadAfiliado(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel)
}

class HelperRegistroActualizacionSeguridadAfiliadoHomeImpl : HelperRegistroActualizacionSeguridadAfiliadoHome {

    override suspend fun registrarSeguridadAfiliado(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) {

    }

}