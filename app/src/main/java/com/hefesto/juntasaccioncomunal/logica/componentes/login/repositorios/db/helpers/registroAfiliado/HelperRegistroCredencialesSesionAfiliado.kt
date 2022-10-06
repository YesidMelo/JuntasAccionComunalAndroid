package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers.registroAfiliado

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.AfiliadoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CorreoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CredencialesSesionDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.entities.login.AfiliadoEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.mapper.traerCredencialesSesionEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.login.registrarAfiliado.AfiliadoARegistrarModel
import javax.inject.Inject

class HelperRegistroCredencialesSesionAfiliado constructor(
    @JvmField @Inject var afiliadoDao: AfiliadoDao,
    @JvmField @Inject var correoDao: CorreoDao,
    @JvmField @Inject var credencialesSesionDao: CredencialesSesionDao
) {

    //region variables
    private lateinit var afiliadoARegistrarModel: AfiliadoARegistrarModel
    //endregion

    fun conAfiliadoARegistrarModel(afiliadoARegistrarModel: AfiliadoARegistrarModel) : HelperRegistroCredencialesSesionAfiliado {
        this.afiliadoARegistrarModel = afiliadoARegistrarModel
        return this
    }

    suspend fun guardarCredencialesSesion() {
        guardarCredenciales()
        vincularAfiliadoConCredenciales()
    }

    //region metodos privados

    private fun guardarCredenciales() {
        val credencialesSesionEntity = afiliadoARegistrarModel.traerCredencialesSesionEntity()
        credencialesSesionEntity.correoId = traerCorreoId()
        credencialesSesionDao.insertarElemento(elemento = credencialesSesionEntity)
    }

    private fun vincularAfiliadoConCredenciales() {
        val afiliadoEntity = traerAfiliadoEntity()!!
        afiliadoEntity.credencialesSesion = traerCredencialesSesionId()
        afiliadoDao.actualizarElemento(elemento = afiliadoEntity)
    }

    private fun traerCorreoId() : Int? {
        return correoDao.traerId(correo = afiliadoARegistrarModel.correo!!)
    }

    private fun traerAfiliadoEntity() : AfiliadoEntity? {
        return afiliadoDao.traerAfiliadoEntityPorTipoDocumentoYDocumento(
            tipoDocumento = afiliadoARegistrarModel.tipoDocumento!!.tipoDocumento.traerId(),
            documento = afiliadoARegistrarModel.numeroDocumento!!
        )
    }

    private fun traerCredencialesSesionId() : Int? {
        return credencialesSesionDao.traerRegistroId(correo = afiliadoARegistrarModel.correo!!)
    }

    //endregion
}