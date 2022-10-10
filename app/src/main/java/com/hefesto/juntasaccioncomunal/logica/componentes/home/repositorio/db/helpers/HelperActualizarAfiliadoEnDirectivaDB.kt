package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers

import com.hefesto.juntasaccioncomunal.fuentesDatos.cache.MemoriaCache
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Jac_EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Jac_EstadoAfiliacionEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.traerAfiliado_Jac_EstadoAfiliacionEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.home.AfiliadoEnDirectivaModificadoModel
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioEnSesionModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.IdentificadorElementosCacheEnum
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface HelperActualizarAfiliadoEnDirectivaDB {
    fun actualizarAfiliadoEnDirectiva(afiliadoEnDirectivaModificadoModel: AfiliadoEnDirectivaModificadoModel) : Flow<Boolean?>
}

class HelperActualizarAfiliadoEnDirectivaDBImpl constructor(
    @JvmField @Inject var afiliadoJacEstadoafiliaciondao: Afiliado_Jac_EstadoAfiliacionDao,
    @JvmField @Inject var memoriaCache: MemoriaCache
) : HelperActualizarAfiliadoEnDirectivaDB {

    override fun actualizarAfiliadoEnDirectiva(afiliadoEnDirectivaModificadoModel: AfiliadoEnDirectivaModificadoModel): Flow<Boolean?> = flow {
        emit(null)
        val jacId = traerJacId()?:return@flow
        val entity = afiliadoEnDirectivaModificadoModel.traerAfiliado_Jac_EstadoAfiliacionEntity()
        entity.jacId = jacId
        afiliadoJacEstadoafiliaciondao.insertarElemento(elemento = entity)
        delay(5000)
        emit(true)
    }

    //region metodos privados
    private fun traerJacId() : Int? {
        val usuarioSesion = memoriaCache.traerObjeto<UsuarioEnSesionModel>(IdentificadorElementosCacheEnum.USUARIO_EN_SESION)
        return usuarioSesion?.jacId
    }
    //endregion

}