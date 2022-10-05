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
        if(traerDireccionId() != null) return
        val direccionEntity = afiliadoARegistrarModel.traerDireccionEntity()
        direccionDao.insertarElemento(elemento = direccionEntity)
    }

    private fun guardarRegistroAfiliadoDireccion() {
        if(traerRegistroAfiliadoDirecion() != null) return
        afiliadoDireccionDao.insertarElemento(elemento = Afiliado_Direccion_Entity(
            afiliadoId = traerAfiliadoId(),
            direccionId = traerDireccionId()
        ))
    }

    private fun guardarRegistroJacAfiliadoDireccion() {
        if(traerRegistroJACAfiliadoDireccion() != null) return
        jacAfiliadoDireccionDao.insertarElemento(elemento = Jac_Afiliado_Direccion_Entity(
            jacId = afiliadoARegistrarModel.jacSeleccionado!!.id,
            direccionId = traerDireccionId(),
            afiliadoId = traerAfiliadoId(),
            fechaInscripcion = afiliadoARegistrarModel.fechaInscripcion!!.convertirAFormato(FormatosFecha.ISO_8610)
        ))
    }

    private fun traerAfiliadoId() : Int? {
        return afiliadoDao.traerIdPorTipoDocumentoYDocumento(
            tipoDocumento = afiliadoARegistrarModel.tipoDocumento!!.tipoDocumento.traerId(),
            documento = afiliadoARegistrarModel.numeroDocumento!!
        )
    }

    private fun traerDireccionId() : Int? {
        return direccionDao.traerIdPorDireccion(direccion = afiliadoARegistrarModel.direccion!!)
    }

    private fun traerRegistroAfiliadoDirecion() : Int? {
        return afiliadoDireccionDao.traerNumeroRegistro(
            afiliadoId = traerAfiliadoId()!!,
            direccionId = traerDireccionId()!!
        )
    }

    private fun traerRegistroJACAfiliadoDireccion() : Int? {
        return jacAfiliadoDireccionDao.traerNumeroRegistro(
            jacId = afiliadoARegistrarModel.jacSeleccionado!!.id,
            direccionId = traerDireccionId()!!,
            afiliadoId = traerAfiliadoId()!!,
        )
    }

    //endregion
}