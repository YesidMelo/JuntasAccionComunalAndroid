package com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.helpers.registroAfiliado

import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CorreoDao
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CredencialesSesionDao
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.traerCorreoEntity
import com.hefesto.juntasaccioncomunal.logica.componentes.home.repositorio.db.mappers.traerCredencialesSesionEntity
import com.hefesto.juntasaccioncomunal.logica.modelos.home.registroAfiliado.CompiladoInformacionAfiliadoParaRegistroModel
import javax.inject.Inject

interface HelperRegistroActualizacionSeguridadAfiliadoHome {
    suspend fun registrarSeguridadAfiliado(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel)
}

class HelperRegistroActualizacionSeguridadAfiliadoHomeImpl constructor(
    @JvmField @Inject var correoDao: CorreoDao,
    @JvmField @Inject var credencialesSesionDao: CredencialesSesionDao
) : HelperRegistroActualizacionSeguridadAfiliadoHome {

    override suspend fun registrarSeguridadAfiliado(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) {
        registrarCorreo(compiladoInformacionAfiliadoParaRegistroModel = compiladoInformacionAfiliadoParaRegistroModel)
        registrarCredenciales(compiladoInformacionAfiliadoParaRegistroModel = compiladoInformacionAfiliadoParaRegistroModel)
    }

    //region metodos privados
    //region correo

    private fun registrarCorreo(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) {
        val correoEntity = compiladoInformacionAfiliadoParaRegistroModel.traerCorreoEntity()
        if (correoEntity.registro == null ){
            correoDao.insertarElemento(elemento = correoEntity)
            val id = correoDao.traerId(correo = correoEntity.correo!!)
            compiladoInformacionAfiliadoParaRegistroModel.contactoParaRegistrarModel?.correoElectronicoId = id
            return
        }

        correoDao.actualizarElemento(elemento = correoEntity)
    }

    private fun registrarCredenciales(compiladoInformacionAfiliadoParaRegistroModel: CompiladoInformacionAfiliadoParaRegistroModel) {
        val credencialesSesionEntity = compiladoInformacionAfiliadoParaRegistroModel.traerCredencialesSesionEntity()
        if (credencialesSesionEntity.registro == null){
            credencialesSesionDao.insertarElemento(elemento = credencialesSesionEntity)
            val id = credencialesSesionDao.traerRegistroId(correo = compiladoInformacionAfiliadoParaRegistroModel.contactoParaRegistrarModel!!.correo!!)
            compiladoInformacionAfiliadoParaRegistroModel.seguridadParaRegistroModel?.credencialesSesionId = id
            return
        }
        if (credencialesSesionEntity.contrasenia.isNullOrEmpty()) return
        credencialesSesionDao.actualizarElemento(elemento = credencialesSesionEntity)
    }

    //endregion
    //endregion

}