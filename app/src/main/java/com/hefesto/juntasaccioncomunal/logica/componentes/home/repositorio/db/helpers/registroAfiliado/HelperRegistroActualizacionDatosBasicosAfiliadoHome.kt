package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.registroAfiliado

import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.CompiladoInformacionAfiliadoParaRegistroModel

interface HelperRegistroActualizacionDatosBasicosAfiliadoHome {
    suspend fun registarDatosBasicos(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel)
}

class HelperRegistroActualizacionDatosBasicosAfiliadoHomeImpl : HelperRegistroActualizacionDatosBasicosAfiliadoHome {

    override suspend fun registarDatosBasicos(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) {

    }

}