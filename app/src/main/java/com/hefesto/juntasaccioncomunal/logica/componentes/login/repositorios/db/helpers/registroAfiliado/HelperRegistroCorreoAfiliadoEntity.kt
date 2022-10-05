package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Correo_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CorreoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Correo_Entity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper.traerCorreoEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import javax.inject.Inject

class HelperRegistroCorreoAfiliadoEntity constructor(
    @JvmField @Inject var afiliadoCorreoDao: Afiliado_Correo_Dao,
    @JvmField @Inject var afiliadoDao: AfiliadoDao,
    @JvmField @Inject var correoDao: CorreoDao
) {

    //region variables
    private lateinit var afiliadoARegistrarModel: AfiliadoARegistrarModel
    //endregion

    suspend fun guardarCorreo() {
        registrarCorreo()
        vincularAfiliadoACorreo()
    }

    fun conAfiliadoARegistrarModel(afiliadoARegistrarModel: AfiliadoARegistrarModel) : HelperRegistroCorreoAfiliadoEntity {
        this.afiliadoARegistrarModel = afiliadoARegistrarModel
        return this
    }


    //region metodos privados

    private fun registrarCorreo() {
        val correoEntity = afiliadoARegistrarModel.traerCorreoEntity()
        correoDao.insertarElemento(correoEntity)
    }

    private fun vincularAfiliadoACorreo() {
        val correoId = correoDao.traerId(correo = afiliadoARegistrarModel.correo!!)
        val afiliadoId = afiliadoDao.traerIdPorTipoDocumentoYDocumento(
            tipoDocumento = afiliadoARegistrarModel.tipoDocumento!!.tipoDocumento.traerId(),
            documento = afiliadoARegistrarModel.numeroDocumento!!
        )
        val afiliadoCorreoEntity = Afiliado_Correo_Entity(
            registro = null,
            afiliadoId = afiliadoId,
            correoId = correoId
        )
        afiliadoCorreoDao.insertarElemento(afiliadoCorreoEntity)
    }
    //endregion
}