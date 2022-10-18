package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.registroAfiliado

import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Jac_Comite_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Jac_EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.traerAfiliadoJACEstadoAfiliadoEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.traerAfiliadoJacComiteEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.CompiladoInformacionAfiliadoParaRegistroModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.IdentificadorElementosCacheEnum
import javax.inject.Inject

interface HelperRegistroActualizacionAfiliadoDetalleEnJacHome {
    suspend fun registrarDetalleAfiliadoEnJac(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel)
}

class HelperRegistroActualizacionAfiliadoDetalleEnJacHomeImpl constructor(
    @JvmField @Inject var afiliadoJacComiteDao: Afiliado_Jac_Comite_Dao,
    @JvmField @Inject var afiliadoJacEstadoafiliaciondao: Afiliado_Jac_EstadoAfiliacionDao,
    @JvmField @Inject var memoriaCache: MemoriaCache
) : HelperRegistroActualizacionAfiliadoDetalleEnJacHome {

    override suspend fun registrarDetalleAfiliadoEnJac(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) {
        registrarComite(compiladoInformacionAfiliadoParaRegistroModel = compiladoInformacionAfiliadoParaRegistroModel)
        registrarEstadoAfiliado(compiladoInformacionAfiliadoParaRegistroModel = compiladoInformacionAfiliadoParaRegistroModel)
    }

    //region metodos privados
    private fun registrarComite(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) {
        val usuarioEnSesion = memoriaCache.traerObjeto<UsuarioEnSesionModel>(llave = IdentificadorElementosCacheEnum.USUARIO_EN_SESION)?:return
        val afiliadoJacComiteEntity = compiladoInformacionAfiliadoParaRegistroModel.traerAfiliadoJacComiteEntity()
        afiliadoJacComiteEntity.jacId = usuarioEnSesion.jacId
        afiliadoJacComiteDao.insertarElemento(elemento = afiliadoJacComiteEntity)
    }

    private fun registrarEstadoAfiliado(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) {
        val usuarioEnSesion = memoriaCache.traerObjeto<UsuarioEnSesionModel>(llave = IdentificadorElementosCacheEnum.USUARIO_EN_SESION)?:return
        val afiliadoJacEstadoafiliacionentity = compiladoInformacionAfiliadoParaRegistroModel.traerAfiliadoJACEstadoAfiliadoEntity()
        afiliadoJacEstadoafiliacionentity.jacId = usuarioEnSesion.jacId
        afiliadoJacEstadoafiliaciondao.insertarElemento(elemento = afiliadoJacEstadoafiliacionentity)
    }
    //endregion

}