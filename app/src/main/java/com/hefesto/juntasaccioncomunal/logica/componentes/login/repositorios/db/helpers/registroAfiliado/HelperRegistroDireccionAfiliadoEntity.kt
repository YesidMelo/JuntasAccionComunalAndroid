package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Direccion_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.DireccionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Jac_Afiliado_Direccion_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Direccion_Entity
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Jac_Afiliado_Direccion_Entity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper.traerDireccionEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import com.hefesto.juntasaccioncomunal.logica.utilidades.enumeradores.FormatosFecha
import com.hefesto.juntasaccioncomunal.logica.utilidades.extenciones.convertirAFormato
import javax.inject.Inject

class HelperRegistroDireccionAfiliadoEntity constructor(
    @JvmField @Inject var afiliadoDao: AfiliadoDao,
    @JvmField @Inject var afiliadoDireccionDao: Afiliado_Direccion_Dao,
    @JvmField @Inject var direccionDao: DireccionDao,
    @JvmField @Inject var jacAfiliadoDireccionDao: Jac_Afiliado_Direccion_Dao
){
    //region variables
    private lateinit var afiliadoARegistrarModel: AfiliadoARegistrarModel
    //enregion

    fun conAfiliadoARegistrarModel(afiliadoARegistrarModel: AfiliadoARegistrarModel) : HelperRegistroDireccionAfiliadoEntity {
        this.afiliadoARegistrarModel = afiliadoARegistrarModel
        return this
    }

    suspend fun guardarDireccion() {
        guardarRegistroDireccion()
        guardarRegistroAfiliadoDireccion()
        guardarRegistroJacAfiliadoDireccion()
    }

    //region metodos privados
    private fun guardarRegistroDireccion() {
        val direccionEntity = afiliadoARegistrarModel.traerDireccionEntity()
        direccionDao.insertarElemento(elemento = direccionEntity)
    }

    private fun guardarRegistroAfiliadoDireccion() {
        val direccionId = direccionDao.traerIdPorDireccion(direccion = afiliadoARegistrarModel.direccion!!)
        val afiliadoId = afiliadoDao.traerIdPorTipoDocumentoYDocumento(
            tipoDocumento = afiliadoARegistrarModel.tipoDocumento!!.tipoDocumento.traerId(),
            documento = afiliadoARegistrarModel.numeroDocumento!!
        )
        afiliadoDireccionDao.insertarElemento(elemento = Afiliado_Direccion_Entity(
            afiliadoId = afiliadoId,
            direccionId = direccionId
        ))
    }

    private fun guardarRegistroJacAfiliadoDireccion() {
        val direccionId = direccionDao.traerIdPorDireccion(direccion = afiliadoARegistrarModel.direccion!!)
        val afiliadoId = afiliadoDao.traerIdPorTipoDocumentoYDocumento(
            tipoDocumento = afiliadoARegistrarModel.tipoDocumento!!.tipoDocumento.traerId(),
            documento = afiliadoARegistrarModel.numeroDocumento!!
        )
        jacAfiliadoDireccionDao.insertarElemento(elemento = Jac_Afiliado_Direccion_Entity(
            jacId = afiliadoARegistrarModel.jacSeleccionado!!.id,
            direccionId = direccionId,
            afiliadoId = afiliadoId,
            fechaInscripcion = afiliadoARegistrarModel.fechaInscripcion!!.convertirAFormato(FormatosFecha.ISO_8610)
        ))
    }
    //endregion
}