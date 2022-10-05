package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Jac_EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Jac_EstadoAfiliacionEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper.traerAfiliadoEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato

class HelperAfiliadoEntity constructor(
    val afiliadoARegistrarModel: AfiliadoARegistrarModel,
    val afiliado_Jac_EstadoAfiliacionDao : Afiliado_Jac_EstadoAfiliacionDao,
    val afiliadoDao: AfiliadoDao
) {

    suspend fun guardarAfiliado() {
        guardarEntidad()
        guardarRelacionAfiliadoJACEstadoAfiliacion()
    }

    //region metodos privados
    private fun guardarEntidad() {
        val afiliadoEntity = afiliadoARegistrarModel.traerAfiliadoEntity()
        afiliadoDao.insertarElemento(afiliadoEntity)
    }

    private fun guardarRelacionAfiliadoJACEstadoAfiliacion() {
        val afiliadoId = afiliadoDao.traerId(
            tipoDocumento = afiliadoARegistrarModel.tipoDocumento!!.tipoDocumento.traerId(),
            documento = afiliadoARegistrarModel.numeroDocumento!!
        )
        val afiliadoJacEstadoAfiliacion = Afiliado_Jac_EstadoAfiliacionEntity(
            afiliadoId = afiliadoId,
            jacId = afiliadoARegistrarModel.jacSeleccionado?.id,
            estadoAfiliacionId = EstadoAfiliacion.PRE_AFILIADO.traerId(),
            fechaActualizacion = afiliadoARegistrarModel.fechaInscripcion?.convertirAFormato(formato = FormatosFecha.ISO_8610)
        )
        afiliado_Jac_EstadoAfiliacionDao.insertarElemento(elemento = afiliadoJacEstadoAfiliacion)
    }

    //endregion
}