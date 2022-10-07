package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios.db.helpers

import androidx.lifecycle.MutableLiveData
import com.hefesto.juntasaccioncomunal.fuentesDatos.db.daos.login.CredencialesSesionDao
import com.hefesto.juntasaccioncomunal.logica.excepciones.LogicaExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.RevisaCredencialesExcepcion
import com.hefesto.juntasaccioncomunal.logica.excepciones.UsuarioNoEstaRegistradoExcepcion
import com.hefesto.juntasaccioncomunal.logica.modelos.login.iniciarSesion.UsuarioInicioSesionModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HelperIniciarSesion constructor(
    @JvmField @Inject var credencialesSesionDao: CredencialesSesionDao
) {

    //region variables
    private lateinit var usuarioInicioSesionModel: UsuarioInicioSesionModel
    private lateinit var escuchadorExcepciones: MutableLiveData<LogicaExcepcion?>
    //endregion

    fun conUsuarioInicioSesionModel(usuarioInicioSesionModel: UsuarioInicioSesionModel) : HelperIniciarSesion {
        this.usuarioInicioSesionModel = usuarioInicioSesionModel
        return this
    }

    fun conEscuchadorExcepciones(escuchadorExcepciones: MutableLiveData<LogicaExcepcion?>) : HelperIniciarSesion {
        this.escuchadorExcepciones = escuchadorExcepciones
        return this
    }

    fun iniciarSesion() = flow<Boolean?> {
        emit(null)
        delay(5000)
        if (!elCorreoExiste()) { emit(false); return@flow }
        if(!lasCredencialesSonCorrectas()) { emit(false); return@flow }
        emit(true)
    }


    //region metodos privados
    private fun elCorreoExiste() : Boolean {
        val credencialesSesionId = credencialesSesionDao.traerRegistroId(correo = usuarioInicioSesionModel.correo!!)
        if (credencialesSesionId == null) {
            escuchadorExcepciones.postValue(UsuarioNoEstaRegistradoExcepcion())
            return false
        }
        return true
    }

    private fun lasCredencialesSonCorrectas() : Boolean {
        val credencialesSesionView = credencialesSesionDao.traerCredencialesSesionView(
            correo = usuarioInicioSesionModel.correo!!,
            contrasenia = usuarioInicioSesionModel.contrasenia!!
        )

        if (credencialesSesionView == null) {
            escuchadorExcepciones.postValue(RevisaCredencialesExcepcion())
            return false
        }
        return true
    }
    //endregion

}