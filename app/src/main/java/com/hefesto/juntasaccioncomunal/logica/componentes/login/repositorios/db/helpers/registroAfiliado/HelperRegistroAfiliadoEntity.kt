package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Jac_EstadoAfiliacionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Jac_EstadoAfiliacionEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper.traerAfiliadoEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.EstadoAfiliacion
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato
import javax.inject.Inject

class HelperRegistroAfiliadoEntity constructor(
    @JvmField @Inject var afiliado_Jac_EstadoAfiliacionDao : Afiliado_Jac_EstadoAfiliacionDao,
    @JvmField @Inject var afiliadoDao: AfiliadoDao
) {

    //region variables
    private lateinit var afiliadoARegistrarModel: AfiliadoARegistrarModel
    //endregion


    suspend fun guardarAfiliado() {
        guardarEntidad()
        guardarRelacionAfiliadoJACEstadoAfiliacion()
    }

    fun conAfiliadoARegistrarModel(afiliadoARegistrarModel: AfiliadoARegistrarModel) : HelperRegistroAfiliadoEntity {
        this.afiliadoARegistrarModel = afiliadoARegistrarModel
        return this
    }

    //region metodos privados
    private fun guardarEntidad() {
        if (traerAfiliadoId() != null) return
        val afiliadoEntity = afiliadoARegistrarModel.traerAfiliadoEntity()
        afiliadoDao.insertarElemento(afiliadoEntity)
    }

    private fun guardarRelacionAfiliadoJACEstadoAfiliacion() {
        val afiliadoJacEstadoAfiliacion = Afiliado_Jac_EstadoAfiliacionEntity(
            afiliadoId = traerAfiliadoId(),
            jacId = afiliadoARegistrarModel.jacSeleccionado?.id,
            estadoAfiliacionId = EstadoAfiliacion.PRE_AFILIADO.traerId(),
            fechaActualizacion = afiliadoARegistrarModel.fechaInscripcion?.convertirAFormato(formato = FormatosFecha.ISO_8610)
        )
        afiliado_Jac_EstadoAfiliacionDao.insertarElemento(elemento = afiliadoJacEstadoAfiliacion)
    }

    private fun traerAfiliadoId() : Int? {
        return afiliadoDao.traerIdPorTipoDocumentoYDocumento(
            tipoDocumento = afiliadoARegistrarModel.tipoDocumento!!.tipoDocumento.traerId(),
            documento = afiliadoARegistrarModel.numeroDocumento!!
        )
    }

    //endregion
}