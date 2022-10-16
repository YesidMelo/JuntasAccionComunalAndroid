package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.registroAfiliado

import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.CompiladoInformacionAfiliadoParaRegistroModel

interface HelperRegistroActualizacionContactoAfiliadoHome {
    suspend fun registrarContacto(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel)
}

class HelperRegistroActualizacionContactoAfiliadoHomeImpl : HelperRegistroActualizacionContactoAfiliadoHome{

    override suspend fun registrarContacto(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) {

    }
}