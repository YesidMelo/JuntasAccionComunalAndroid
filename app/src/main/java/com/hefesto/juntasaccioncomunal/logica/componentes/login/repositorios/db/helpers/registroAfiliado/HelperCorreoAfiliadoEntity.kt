package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.Afiliado_Correo_Dao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CorreoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.Afiliado_Correo_Entity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper.traerCorreoEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel

class HelperCorreoAfiliadoEntity constructor(
    val afiliadoARegistrarModel: AfiliadoARegistrarModel,
    val afiliadoCorreoDao: Afiliado_Correo_Dao,
    val afiliadoDao: AfiliadoDao,
    val correoDao: CorreoDao
) {

    suspend fun guardarCorreo() {
        registrarCorreo()
        vincularAfiliadoACorreo()
    }

    //region metodos privados

    private fun registrarCorreo() {
        val correoEntity = afiliadoARegistrarModel.traerCorreoEntity()
        correoDao.insertarElemento(correoEntity)
    }

    private fun vincularAfiliadoACorreo() {
        val correoId = correoDao.traerId(correo = afiliadoARegistrarModel.correo!!)
        val afiliadoId = afiliadoDao.traerId(
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