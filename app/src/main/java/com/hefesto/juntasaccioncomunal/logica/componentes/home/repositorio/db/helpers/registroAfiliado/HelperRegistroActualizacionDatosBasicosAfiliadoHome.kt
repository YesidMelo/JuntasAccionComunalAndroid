package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.registroAfiliado

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.traerAfiliadoEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.CompiladoInformacionAfiliadoParaRegistroModel
import javax.inject.Inject

interface HelperRegistroActualizacionDatosBasicosAfiliadoHome {
    suspend fun registarDatosBasicos(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel)
}

class HelperRegistroActualizacionDatosBasicosAfiliadoHomeImpl constructor(
    @JvmField @Inject var afiliadoDao: AfiliadoDao
) : HelperRegistroActualizacionDatosBasicosAfiliadoHome {

    override suspend fun registarDatosBasicos(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) {
        registrarAfiliado(compiladoInformacionAfiliadoParaRegistroModel = compiladoInformacionAfiliadoParaRegistroModel)
    }

    //region metodos privados
    private fun registrarAfiliado(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) {
        val afiliadoEntity = compiladoInformacionAfiliadoParaRegistroModel.traerAfiliadoEntity()
        if (afiliadoEntity.afiliadoId == null) {
            afiliadoDao.insertarElemento(elemento = afiliadoEntity)
            val id = afiliadoDao.traerIdPorTipoDocumentoYDocumento(tipoDocumento = afiliadoEntity.tipoDocumento!!, documento = afiliadoEntity.documento!!)
            compiladoInformacionAfiliadoParaRegistroModel.datosBasicosParaRegistrarModel?.afiliadoId = id
            return
        }
        afiliadoDao.actualizarElemento(elemento = afiliadoEntity)
    }

    //endregion

}